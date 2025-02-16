package com.prography.assignment.api.init.service.command;

import com.prography.assignment.api.init.controller.request.InitPostRequest;

public record InitPostCommand(
        int seed,
        int quantity
) {
    public static InitPostCommand of(final InitPostRequest request){
        return new InitPostCommand(request.seed(), request.quantity());
    }
}
