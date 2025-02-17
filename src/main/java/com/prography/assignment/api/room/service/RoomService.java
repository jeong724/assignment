package com.prography.assignment.api.room.service;

import com.prography.assignment.api.room.service.command.RoomAttendPostCommand;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.room.service.response.RoomGetResponse;
import com.prography.assignment.api.room.service.response.RoomWithDateResponse;
import com.prography.assignment.api.user.service.UserFinder;
import com.prography.assignment.api.userroom.service.UserRoomUpdater;
import com.prography.assignment.api.userroom.service.UserRoomValidator;
import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.BadRequestException;
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

    @Transactional
    public void createRoom(final RoomPostCommand command) {
        User host = userFinder.findById(command.userId())
                .orElseThrow(()-> new BadRequestException(BusinessErrorCode.BAD_REQUEST));

        if(!validateCreateRoom(host)) {
            throw new BadRequestException(BusinessErrorCode.BAD_REQUEST);
        }

        //룸 생성
        Room room = roomUpdater.save(Room.create(command.title(), RoomType.valueOf(command.roomType()), RoomStatus.WAIT, host));

        //host 저장
        userRoomUpdater.save(UserRoom.create(Team.RED, host, room));
    }

    @Transactional(readOnly = true)
    public RoomGetResponse getAllRooms(final int size, final int page){
        Page<Room> rooms = roomFinder.findRooms(size, page);

        return RoomGetResponse.of(rooms);
    }

    @Transactional(readOnly = true)
    public RoomWithDateResponse getRoom(final int roomId){
        Room room = roomFinder.findRoom(roomId)
                .orElseThrow(() -> new BadRequestException(BusinessErrorCode.BAD_REQUEST));

        return RoomWithDateResponse.of(room);
    }

    @Transactional
    public void attendRoom(final RoomAttendPostCommand command){
        User user = userFinder.findById(command.userId())
                .orElseThrow(()-> new BadRequestException(BusinessErrorCode.BAD_REQUEST));

        Room room = roomFinder.findRoom(command.roomId())
                .orElseThrow(()-> new BadRequestException(BusinessErrorCode.BAD_REQUEST));

        if (!validateAttend(room, user)){
            throw new BadRequestException(BusinessErrorCode.BAD_REQUEST);
        }

        userRoomUpdater.save();
    }

    private boolean validateCreateRoom(final User host) {

        // 활성 상태아니라면 룸 생성 x
        if (!(host.getStatus() == UserStatus.ACTIVE)) {
            return false;
        }

        //유저가 다른 룸에 존재하면 룸 생성x
        if (userRoomValidator.userRoomExists(host)) {
            return false;
        }

        return true;
    }

    private boolean validateAttend(final Room room, User user){

        //룸이 대기상태가 아니라면
        if (room.getStatus() != RoomStatus.WAIT){
            return false;
        }

        // 활성 상태아니라면 룸 참가 x
        if (!(user.getStatus() == UserStatus.ACTIVE)) {
            return false;
        }

        //유저가 다른 룸에 존재하면 룸 참가x
        if (userRoomValidator.userRoomExists(user)) {
            return false;
        }

        //참가하려는 방의 인원이 찼을때
        int currentAttendance = userRoomValidator.countUser(room);
        if (currentAttendance >= room.getCapacity()){
            return false;
        }

        return true;
    }


}
