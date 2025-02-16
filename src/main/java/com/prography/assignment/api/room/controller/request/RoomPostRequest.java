package com.prography.assignment.api.room.controller.request;

import jakarta.validation.constraints.NotBlank;

public record RoomPostRequest(
        @NotBlank
        int userId,
        @NotBlank
        String roomType,
        @NotBlank
        String title
) {
}
