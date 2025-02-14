package com.prography.assignment.api.common;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.common.health.HealthController;
import com.prography.assignment.common.code.CommonSuccessCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(HealthController.class) // ✅ 특정 컨트롤러만 테스트
@DisplayName("Health API 테스트")
class HealthApiTest {

    @Autowired
    private MockMvc mockMvc; // ✅ HTTP 요청을 모의 실행하는 객체

    @Test
    @DisplayName("/health API를 테스트 한다")
    void getHealthStatus() throws Exception {
        // When & Then
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk());
    }
}

