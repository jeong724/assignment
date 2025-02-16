package com.prography.assignment.api.common.init.service;

import com.prography.assignment.api.common.init.service.command.InitPostCommand;
import com.prography.assignment.api.common.init.service.response.FakerGetDataResponse;
import com.prography.assignment.api.common.init.service.response.FakerGetParseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class FakerApiUserDataProviderImpl implements UserDataProvider {

    private final static String URL = "https://fakerapi.it/api/v1/users?_seed=%s&_quantity=%s&_locale=ko_KR";

    @Override
    public List<FakerGetParseResponse> fetchUsers(InitPostCommand command) {

        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(String.format(URL, command.seed(), command.quantity()));

        ResponseEntity<FakerGetDataResponse> response;

        try {
            response = restTemplate.getForEntity(uri, FakerGetDataResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Fetching Data Failed: " + e.getMessage(), e);
        }

        return response.getBody().data();
    }
}
