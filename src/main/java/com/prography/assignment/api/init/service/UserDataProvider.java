package com.prography.assignment.api.init.service;

import com.prography.assignment.api.init.service.command.InitPostCommand;
import com.prography.assignment.api.init.service.response.FakerGetParseResponse;

import java.util.List;

public interface UserDataProvider {
   List<FakerGetParseResponse> fetchUsers(InitPostCommand command);
}
