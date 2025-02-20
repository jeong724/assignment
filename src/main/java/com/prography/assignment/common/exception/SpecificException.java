package com.prography.assignment.common.exception;

import com.prography.assignment.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class SpecificException extends RuntimeException {

    private final ErrorCode errorCode;

    public SpecificException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
