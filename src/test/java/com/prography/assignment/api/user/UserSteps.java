package com.prography.assignment.api.user;

import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.model.UserStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserSteps {

    public static List<User> 유저_목록_생성(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> User.builder()
                        .name("tester" + i)
                        .fakerId(i)
                        .status(UserStatus.WAIT)
                        .email("tester" + i + "@gmail.com")
                        .build())
                .collect(Collectors.toList());
    }
}
