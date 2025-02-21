package com.prography.assignment.api.init;

import com.prography.assignment.api.init.controller.request.InitPostRequest;
import com.prography.assignment.api.init.service.command.InitPostCommand;

public class InitSteps {

    public static InitPostCommand 초기화_요청() {
        return InitPostCommand.of(new InitPostRequest(123, 70));
    }

}
