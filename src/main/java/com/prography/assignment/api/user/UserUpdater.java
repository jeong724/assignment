package com.prography.assignment.api.user;

import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;

    public void save(List<User> user) {
        userRepository.saveAll(user);
    }
}
