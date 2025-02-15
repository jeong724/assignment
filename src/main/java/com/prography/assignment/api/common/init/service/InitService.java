package com.prography.assignment.api.common.init.service;

import com.prography.assignment.api.common.init.service.command.InitPostCommand;
import com.prography.assignment.api.common.init.service.response.FakerGetParseResponse;
import com.prography.assignment.api.user.UserUpdater;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.model.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InitService {

    private final Initializer initializer;
    private final UserDataProvider provider;
    private final UserUpdater userUpdater;


    @Transactional
    public void init(final InitPostCommand command) {
        initializer.init(); //db 초기화

        List<User> userList = provider.fetchUsers(command).stream()
                .sorted(Comparator.comparing(FakerGetParseResponse::fakerId)) // fakerId 기준 정렬
                .map(response -> User.create(response.name(), response.fakerId(), response.email(), determineStatus(response.fakerId())))
                .collect(Collectors.toList());


        userUpdater.save(userList);
    }

    private UserStatus determineStatus(Integer fakerId) {
        if (fakerId <= 30) {
            return UserStatus.ACTIVE;
        } else if (fakerId <= 60) {
            return UserStatus.WAIT;
        } else {
            return UserStatus.NON_ACTIVE;
        }
    }
}
