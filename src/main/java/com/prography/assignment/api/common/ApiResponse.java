package com.prography.assignment.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prography.assignment.common.code.ErrorCode;
import com.prography.assignment.common.code.SuccessCode;
import lombok.*;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final Integer code;
    private final String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T result;

    //성공한 경우
    public static <T> ApiResponse<T> onSuccess(SuccessCode successCode){
        return new ApiResponse<>(successCode.getHttpStatus().value(), successCode.getMessage());
    }

    public static <T> ApiResponse<T> onSuccess(SuccessCode successCode, T data){
        return new ApiResponse<>(successCode.getHttpStatus().value(), successCode.getMessage(), data);
    }

    //실패한 경우
    public static <T> ApiResponse<T> onFailure(ErrorCode errorCode){
        return new ApiResponse<>(errorCode.getHttpStatus().value(), errorCode.getMessage());
    }
}


