package com.prography.assignment.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BusinessErrorCode implements ErrorCode{

    BAD_REQUEST(HttpStatus.OK, 201,"불가능한 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500,"에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}