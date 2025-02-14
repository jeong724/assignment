package com.prography.assignment.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonSuccessCode implements SuccessCode {
    OK(HttpStatus.OK, "API 요청이 성공했습니다."),
    BAD_REQUEST(HttpStatus.CREATED, "불가능한 요청입니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
