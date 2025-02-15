package com.prography.assignment.api.common.init.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.common.init.controller.request.InitPostRequest;
import com.prography.assignment.api.common.init.service.InitService;
import com.prography.assignment.common.code.CommonSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/init")
public class InitController {

    private final InitService initService;

    @PostMapping
    public ApiResponse<Void> init(
            @RequestBody InitPostRequest request
    ) {
        initService.init(request.seed(), request.quantity());
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }
}
