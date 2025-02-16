package com.prography.assignment.api.init.service.response;

import java.util.List;

public record FakerGetDataResponse(
        List<FakerGetParseResponse> data
) {
}
