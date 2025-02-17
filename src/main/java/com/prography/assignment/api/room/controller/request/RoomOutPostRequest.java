package com.prography.assignment.api.room.controller.request;

import jakarta.validation.constraints.NotBlank;

public record RoomOutPostRequest(
        @NotBlank
        int userId
) {
}
