package com.prography.assignment.api.health;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.common.code.CommonSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "HEALTH API", description = "헬스 체크 API 입니다. ")
@RestController
@RequestMapping("/health")
public class HealthController {

    @Operation(
            summary = "헬스 체크 API",
            description = "서버의 상태를 체크하는 API 입니다. "
    )

    @GetMapping
    public ApiResponse<Void> getHealthStatus() {
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }

}

