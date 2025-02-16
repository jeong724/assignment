package com.prography.assignment.api.init.controller.request;

import jakarta.validation.constraints.NotBlank;

public record InitPostRequest(
        @NotBlank
        int seed,
        @NotBlank
        int quantity
) {
}
