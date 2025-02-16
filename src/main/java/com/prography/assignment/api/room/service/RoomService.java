package com.prography.assignment.api.room.service;

import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.room.service.response.RoomGetResponse;
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

        if(!validateCreateRoom(host))
            throw new BadRequestException(BusinessErrorCode.BAD_REQUEST);

        //룸 생성
        Room room = roomUpdater.save(Room.create(command.title(), RoomType.valueOf(command.roomType()), RoomStatus.WAIT));

        //host 저장
        userRoomUpdater.save(UserRoom.create(Team.RED, host, room));
    }

    @Transactional(readOnly = true)
    public RoomGetResponse getAllRooms(int size, int page){
        Page<Room> rooms = roomFinder.findRooms(size, page);

    }

    private boolean validateCreateRoom(final User host) {

        // 활성 상태아니라면 룸 생성 x
        if (!(host.getStatus() == UserStatus.ACTIVE))
            return false;

        //유저가 다른 룸에 존재하면 룸 생성x
        if (userRoomValidator.userRoomExists(host))
            return false;

        return true;
    }


}
