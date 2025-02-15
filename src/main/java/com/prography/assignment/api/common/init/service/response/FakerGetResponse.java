package com.prography.assignment.api.common.init.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FakerGetResponse(

        @JsonProperty(value = "id")
        Integer fakerId,

        @JsonProperty(value = "username")
        String name,

        @JsonProperty(value = "email")
        String email
) {
}