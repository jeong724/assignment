package com.prography.assignment.api.user.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.user.service.UserService;
import com.prography.assignment.api.user.service.response.UserGetResponse;
import com.prography.assignment.common.code.CommonSuccessCode;
import com.prography.assignment.common.code.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "USER API", description = "회원에 대한 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @Operation(
            summary = "유저 전체 조회 API",
            description = "모든 회원 정보 데이터를 반환합니다. 페이징 처리를 위해 size와 page를 넘겨주세요"
    )
    @GetMapping
    public ApiResponse<UserGetResponse> getALlUsers(
            @RequestParam final int size,
            @RequestParam final int page
    ){
        UserGetResponse response = userService.getUsers(size, page);

        return ApiResponse.onSuccess(CommonSuccessCode.OK, response);
    }
}
