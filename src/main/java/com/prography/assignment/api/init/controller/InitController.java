package com.prography.assignment.api.init.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.init.controller.request.InitPostRequest;
import com.prography.assignment.api.init.service.InitService;
import com.prography.assignment.api.init.service.command.InitPostCommand;
import com.prography.assignment.common.code.CommonSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "INIT API", description = "초기화 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/init")
public class InitController {

    private final InitService initService;

    @Operation(
            summary = "초기화 API",
            description = "기존에 있던 모든 회원 정보 및 방 정보를 삭제합니다."
    )

    @PostMapping
    public ApiResponse<Void> init(
            @RequestBody InitPostRequest request
    ) {
        initService.init(InitPostCommand.of(request));
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }
}
