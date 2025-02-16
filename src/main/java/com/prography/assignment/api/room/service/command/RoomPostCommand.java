package com.prography.assignment.api.room.service.command;

import com.prography.assignment.api.room.controller.request.RoomPostRequest;

public record RoomPostCommand(
        int userId,
        String roomType,
        String title
) {
    public static RoomPostCommand of(final RoomPostRequest request){
        return new RoomPostCommand(request.userId(), request.roomType(), request.title());
    }
}
