package com.prography.assignment.api.room.service;

import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.SpecificException;
import com.prography.assignment.domain.room.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomResolver {

    private final RoomFinder roomFinder;

    public Room resolveRoom(int roomId) {
        return roomFinder.findRoom(roomId)
                .orElseThrow(() -> new SpecificException(BusinessErrorCode.BAD_REQUEST));
    }
}
