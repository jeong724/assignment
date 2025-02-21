package com.prography.assignment.api.user;

import com.prography.assignment.api.user.service.UserService;
import com.prography.assignment.api.user.service.response.UserGetResponse;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RetrieveUserCommandTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void 전체_유저를_조회한다() {

        // given
        List<User> users = UserSteps.유저_목록_생성(10);
        userRepository.saveAll(users);

        UserGetResponse retrievedUsers = userService.getUsers(5, 1);

        // then
        assertThat(retrievedUsers.users()).hasSize(5);
        assertThat(retrievedUsers.totalElements()).isEqualTo(10);
    }
}
