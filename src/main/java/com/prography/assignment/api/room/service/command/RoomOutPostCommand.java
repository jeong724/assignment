package com.prography.assignment.api.room.service.command;

import com.prography.assignment.api.room.controller.request.RoomOutPostRequest;

public record RoomOutPostCommand(
        int userId,
        int roomId
) {
    public static RoomOutPostCommand of(int roomId, RoomOutPostRequest request){
        return new RoomOutPostCommand(request.userId(), roomId);
    }
}
