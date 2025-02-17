package com.prography.assignment.api.room.service.response;

import com.prography.assignment.common.util.DateTimeUtil;
import com.prography.assignment.domain.room.model.Room;

public record RoomWithDateResponse(
        int id,
        String title,
        int hostId,
        String roomType,
        String status,
        String createdAt,
        String updatedAt
) {
    public static RoomWithDateResponse of(Room room) {
        return new RoomWithDateResponse(
                room.getId(),
                room.getTitle(),
                room.getHost().getId(),
                room.getRoomType().name(),
                room.getStatus().name(),
                DateTimeUtil.format(room.getCreatedAt(), "yyyy-MM-dd HH:mm:ss"),
                DateTimeUtil.format(room.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss")
        );
    }
}