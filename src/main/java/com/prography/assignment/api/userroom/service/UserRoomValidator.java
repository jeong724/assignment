package com.prography.assignment.api.userroom.service;

import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoomValidator {

    private final UserRoomRepository userRoomRepository;

    public boolean userRoomExists(User user) {
        return userRoomRepository.existsByUser(user);
    }

}
