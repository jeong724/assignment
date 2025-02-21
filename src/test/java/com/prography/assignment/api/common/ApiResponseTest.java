package com.prography.assignment.api.common;

import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.code.CommonSuccessCode;
import com.prography.assignment.common.code.ErrorCode;
import com.prography.assignment.common.code.SuccessCode;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void onSuccess_데이터x를_실행한다() {

        // given
        SuccessCode successCode = CommonSuccessCode.OK;

        // when
        ApiResponse<Void> response = ApiResponse.onSuccess(successCode);

        // then
        assertThat(response.getCode()).isEqualTo(successCode.getHttpStatus().value());
        assertThat(response.getMessage()).isEqualTo(successCode.getMessage());
        assertThat(response.getResult()).isNull();
    }

    @Test
    void onSuccess_데이터o를_실행한다() {

        // given
        SuccessCode successCode = CommonSuccessCode.OK;
        String data = "jjeong";

        // when
        ApiResponse<String> response = ApiResponse.onSuccess(successCode, data);

        // then
        assertThat(response.getResult()).isEqualTo(data);
    }

    @Test
    void onFailure를_실행한다() {
        // given
        ErrorCode errorCode = BusinessErrorCode.BAD_REQUEST;

        // when
        ApiResponse<Void> response = ApiResponse.onFailure(errorCode);

        // then
        assertThat(response.getMessage()).isEqualTo(errorCode.getMessage());
    }
}

