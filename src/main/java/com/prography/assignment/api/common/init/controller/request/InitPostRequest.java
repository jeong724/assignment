package com.prography.assignment.api.common.init.controller.request;

import jakarta.validation.constraints.NotBlank;

public record InitPostRequest(
        @NotBlank
        int seed,
        @NotBlank
        int quantity
) {
}
