package com.prography.assignment.api.common;

import com.prography.assignment.common.code.ErrorCode;
import com.prography.assignment.common.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ApiResponse<Void> handleBadRequestException(BadRequestException e){
        return ApiResponse.onFailure(e.getErrorCode());
    }
}
