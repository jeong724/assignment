package com.prography.assignment.api.room.service.command;


import com.prography.assignment.api.room.controller.request.RoomStartPostRequest;

public record RoomStartPostCommand(
        int userId,
        int roomId
) {
    public static RoomStartPostCommand of(RoomStartPostRequest request, int roomId){
        return new RoomStartPostCommand(request.userId(), roomId);
    }
}
