package com.prography.assignment.api.user.service;

import com.prography.assignment.api.user.service.response.UserGetResponse;
import com.prography.assignment.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFinder userFinder;

    @Transactional(readOnly = true)
    public UserGetResponse getUsers(int size, int page){
        Page<User> users= userFinder.findAll(size, page);

        return UserGetResponse.of(users);
    }
}
