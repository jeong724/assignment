package com.prography.assignment.api.common;

import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.SpecificException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SpecificException.class)
    public ApiResponse<Void> handleSpecificException(SpecificException e){
        return ApiResponse.onFailure(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleException(Exception e){
        return ApiResponse.onFailure(BusinessErrorCode.INTERNAL_SERVER_ERROR);
    }
}
