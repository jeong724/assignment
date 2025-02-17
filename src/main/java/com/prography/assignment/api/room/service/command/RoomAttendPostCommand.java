package com.prography.assignment.api.room.service.command;

import com.prography.assignment.api.room.controller.request.RoomAttendPostRequest;

public record RoomAttendPostCommand(
        int userId,
        int roomId
) {
    public static RoomAttendPostCommand of(RoomAttendPostRequest request, int roomId){
        return new RoomAttendPostCommand(request.userId(), roomId);
    }
}
