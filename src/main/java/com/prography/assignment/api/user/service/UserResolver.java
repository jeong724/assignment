package com.prography.assignment.api.user.service;

import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.SpecificException;
import com.prography.assignment.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResolver {

    private final UserFinder userFinder;

    public User resolveUser(int userId) {
        return userFinder.findById(userId)
                .orElseThrow(() -> new SpecificException(BusinessErrorCode.BAD_REQUEST));
    }
}
