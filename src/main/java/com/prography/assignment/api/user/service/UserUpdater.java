package com.prography.assignment.api.user.service;

import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;

    @Transactional
    public void save(List<User> users) {
        userRepository.saveAll(users);
    }
}
