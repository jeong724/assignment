package com.prography.assignment.api.user.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.user.service.UserService;
import com.prography.assignment.api.user.service.response.UserGetResponse;
import com.prography.assignment.common.code.CommonSuccessCode;
import com.prography.assignment.common.code.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<UserGetResponse> getALlUsers(
            @RequestParam final int size,
            @RequestParam final int page
    ){
        UserGetResponse response = userService.getUsers(size, page);

        return ApiResponse.onSuccess(CommonSuccessCode.OK, response);
    }
}
