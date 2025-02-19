package com.prography.assignment.api.room.service;

import com.prography.assignment.api.userroom.service.UserRoomDeleter;
import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.BadRequestException;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RoomFinisher {

    private final UserRoomDeleter userRoomDeleter;
    private final RoomFinder roomFinder;

    @Transactional
    public void finishRoom(int roomId) {

        Room room = roomFinder.findRoom(roomId)
                .orElseThrow(()-> new BadRequestException(BusinessErrorCode.BAD_REQUEST));

        userRoomDeleter.deleteUserRoom(room);
        room.changeRoomStatus(RoomStatus.FINISH);
    }
}
