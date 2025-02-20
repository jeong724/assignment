package com.prography.assignment.api.room.controller.request;

public record RoomPostRequest(
        int userId,
        String roomType,
        String title
) {
}
