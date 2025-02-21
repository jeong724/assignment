package com.prography.assignment.api.init;

import com.prography.assignment.api.init.service.InitService;
import com.prography.assignment.api.init.service.command.InitPostCommand;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.model.UserStatus;
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
public class InitCommandTest {

    @Autowired
    InitService initService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    void 회원_상태가_규칙에_맞게_저장된다() {

        // given
        InitPostCommand command = InitSteps.초기화_요청();

        // when
        initService.init(command);

        // then
        List<User> allMembers = userRepository.findAll();
        assertThat(allMembers).hasSize(command.quantity());

        long activeCount = allMembers.stream()
                .filter(member -> member.getStatus().equals(UserStatus.ACTIVE))
                .count();

        long waitCount = allMembers.stream()
                .filter(member -> member.getStatus().equals(UserStatus.WAIT))
                .count();

        long nonActiveCount = allMembers.stream()
                .filter(member -> member.getStatus().equals(UserStatus.NON_ACTIVE))
                .count();

        assertThat(activeCount).isEqualTo(30);
        assertThat(waitCount).isEqualTo(30);
        assertThat(nonActiveCount).isEqualTo(10);
    }
}
