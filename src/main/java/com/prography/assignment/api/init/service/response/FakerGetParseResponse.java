package com.prography.assignment.api.init.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FakerGetParseResponse(

        @JsonProperty(value = "id")
        Integer fakerId,

        @JsonProperty(value = "username")
        String name,

        @JsonProperty(value = "email")
        String email
) {
}