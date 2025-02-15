package com.prography.assignment.api.common.init.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.common.init.controller.request.InitPostRequest;
import com.prography.assignment.api.common.init.service.InitService;
import com.prography.assignment.api.common.init.service.command.InitPostCommand;
import com.prography.assignment.common.code.CommonSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class InitController {

    private final InitService initService;

    @PostMapping("/init")
    public ApiResponse<Void> init(
            @RequestBody InitPostRequest request
    ) {
        initService.init(InitPostCommand.of(request));
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }
}
