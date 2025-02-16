package com.prography.assignment.api.common.init.service.response;

import java.util.List;

public record FakerGetDataResponse(
        List<FakerGetParseResponse> data
) {
}
