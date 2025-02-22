package com.prography.assignment.api.room.service;

import com.prography.assignment.api.room.service.command.RoomAttendPostCommand;
import com.prography.assignment.api.room.service.command.RoomOutPostCommand;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.room.service.command.RoomStartPostCommand;
import com.prography.assignment.api.room.service.response.RoomGetResponse;
import com.prography.assignment.api.room.service.response.RoomWithDateResponse;
import com.prography.assignment.api.user.service.UserFinder;
import com.prography.assignment.api.user.service.UserResolver;
import com.prography.assignment.api.userroom.service.UserRoomDeleter;
import com.prography.assignment.api.userroom.service.UserRoomUpdater;
import com.prography.assignment.api.userroom.service.UserRoomValidator;
import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.SpecificException;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import com.prography.assignment.domain.room.model.RoomType;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.model.UserStatus;
import com.prography.assignment.domain.userroom.model.Team;
import com.prography.assignment.domain.userroom.model.UserRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final UserFinder userFinder;
    private final UserRoomValidator userRoomValidator;
    private final RoomUpdater roomUpdater;
    private final UserRoomUpdater userRoomUpdater;
    private final RoomFinder roomFinder;
    private final UserRoomDeleter userRoomDeleter;
    private final RoomTimeOutScheduler roomTimeOutScheduler;
    private final RoomResolver roomResolver;
    private final UserResolver userResolver;

    @Transactional
    public int createRoom(final RoomPostCommand command) {
        User host = userResolver.resolveUser(command.userId());

        if(!validateCreateRoom(host)) {
            throw new SpecificException(BusinessErrorCode.BAD_REQUEST);
        }

        Room room = roomUpdater.save(Room.create(command.title(), RoomType.valueOf(command.roomType()), RoomStatus.WAIT, host));

        userRoomUpdater.save(UserRoom.create(Team.RED, host, room));

        return room.getId();
    }

    @Transactional(readOnly = true)
    public RoomGetResponse getAllRooms(final int size, final int page){
        Page<Room> rooms = roomFinder.findRooms(size, page);

        return RoomGetResponse.of(rooms);
    }

    @Transactional(readOnly = true)
    public RoomWithDateResponse getRoom(final int roomId){
        Room room = roomResolver.resolveRoom(roomId);

        return RoomWithDateResponse.of(room);
    }

    @Transactional
    public void attendRoom(final RoomAttendPostCommand command){
        User user = userResolver.resolveUser(command.userId());
        Room room = roomResolver.resolveRoom(command.roomId());

        if (!validateAttend(room, user)){
            throw new SpecificException(BusinessErrorCode.BAD_REQUEST);
        }

        userRoomUpdater.save(UserRoom.create(availableTeam(room),user, room));
    }

    @Transactional
    public void outRoom(final RoomOutPostCommand command){
        User user = userResolver.resolveUser(command.userId());
        Room room = roomResolver.resolveRoom(command.roomId());

        if (!validateOut(user, room)){
            throw new SpecificException(BusinessErrorCode.BAD_REQUEST);
        }

        userRoomDeleter.deleteUser(user);

        isHostOut(user, room);
    }

    @Transactional
    public void startRoom(RoomStartPostCommand command){
        User user = userResolver.resolveUser(command.userId());
        Room room = roomResolver.resolveRoom(command.roomId());

        if (!validateStartGame(user, room)){
            throw new SpecificException(BusinessErrorCode.BAD_REQUEST);
        }

        room.changeRoomStatus(RoomStatus.PROGRESS);

        roomTimeOutScheduler.finish(room.getId());
    }

    private boolean validateCreateRoom(final User host) {

        //1. 활성 상태아니라면 룸 생성 x
        if (!(host.getStatus() == UserStatus.ACTIVE)) {
            return false;
        }

        //2. 유저가 다른 룸에 존재하면 룸 생성x
        if (userRoomValidator.userRoomExists(host)) {
            return false;
        }

        return true;
    }

    private boolean validateAttend(final Room room, final User user){

        //1. 룸이 대기상태가 아니라면
        if (room.getStatus() != RoomStatus.WAIT){
            return false;
        }

        //2. 활성 상태아니라면 룸 참가 x
        if (!(user.getStatus() == UserStatus.ACTIVE)) {
            return false;
        }

        //3. 유저가 다른 룸에 존재하면 룸 참가x
        if (userRoomValidator.userRoomExists(user)) {
            return false;
        }

        //4. 참가하려는 방의 인원이 찼을때
        int currentAttendance = userRoomValidator.countUser(room);
        if (currentAttendance >= room.getCapacity()){
            return false;
        }

        return true;
    }

    private Team availableTeam(final Room room){
        int redAttendance = userRoomValidator.countRedTeam(room);

        if (redAttendance >= room.getCapacity()/2){
            return Team.BLUE;
        }
        return Team.RED;
    }

    private boolean validateOut(final User user, final Room room){

        //1. 유저가 룸에 없을떄
        if (!userRoomValidator.isUserInRoom(user,room)) {
            return false;
        }

        //2.방이 PROGRESS 또는 FINISH 일떄
        if (room.getStatus() != RoomStatus.WAIT){
            return false;
        }

        return true;
    }

    //호스트가 나갈때
    private void isHostOut(User user, Room room){
        if (user.equals(room.getHost())){
            userRoomDeleter.deleteUserRoom(room);
            room.changeRoomStatus(RoomStatus.FINISH);
        }
    }

    private boolean validateStartGame(User user, Room room){

        //유저가 호스트인지
        if (!user.equals(room.getHost())) {
            return false;
        }

        //방 인원이 꽉 찼을때
        int currentAttendance = userRoomValidator.countUser(room);
        if (currentAttendance != room.getCapacity()){
            return false;
        }

        //룸 상태가 wait일때
        if (room.getStatus() != RoomStatus.WAIT){
            return false;
        }

        return true;
    }

}
