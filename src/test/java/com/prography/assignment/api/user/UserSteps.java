package com.prography.assignment.api.user;

import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.model.UserStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserSteps {

    public static List<User> 유저_목록_생성(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> User.create("tester" + i, i, "tester"+i+"@gmail.com", UserStatus.WAIT))
                .collect(Collectors.toList());
    }

    public static User 유저_STATUS_ACTIVE_객체_생성2(){
        return User.create("tester2", 2, "tester1@gmail.com", UserStatus.ACTIVE);
    }

    public static User 유저_STATUS_ACTIVE_객체_생성(){
        return User.create("tester1", 1, "tester1@gmail.com", UserStatus.ACTIVE);
    }

}
