package com.prography.assignment.api.user.service;

import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFinder {

    private final UserRepository userRepository;

    public Page<User> findAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return userRepository.findAllByOrderByIdAsc(pageable);
    }
}
