package com.prography.assignment.api.room.service.response;

import com.prography.assignment.domain.room.model.Room;

public record RoomResponse(
        int id,
        String title,
        int hostId,
        String roomType,
        String status
) {
    public static RoomResponse of(Room room){
        return new RoomResponse(room.getId(), room.getTitle(), room.getHost().getId(), room.getRoomType().name(), room.getStatus().name());
    }
}
