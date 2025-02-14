package com.prography.assignment.api.common;

import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.code.CommonSuccessCode;
import com.prography.assignment.common.code.ErrorCode;
import com.prography.assignment.common.code.SuccessCode;
import com.prography.assignment.common.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void onSuccessWithoutData() {

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
    void onSuccessWithData() {

        // given
        SuccessCode successCode = CommonSuccessCode.OK;
        String data = "jjeong";

        // when
        ApiResponse<String> response = ApiResponse.onSuccess(successCode, data);

        // then
        assertThat(response.getResult()).isEqualTo(data);
    }

    @Test
    void onFailure() {
        // given
        ErrorCode errorCode = BusinessErrorCode.BAD_REQUEST;

        // when
        ApiResponse<Void> response = ApiResponse.onFailure(errorCode);

        // then
        assertThat(response.getCode()).isEqualTo(errorCode.getHttpStatus().value());
        assertThat(response.getMessage()).isEqualTo(errorCode.getMessage());
    }
}

