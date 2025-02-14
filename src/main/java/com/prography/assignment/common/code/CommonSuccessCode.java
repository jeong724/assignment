package com.prography.assignment.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonSuccessCode implements SuccessCode {

    OK(HttpStatus.OK, "API 요청이 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}