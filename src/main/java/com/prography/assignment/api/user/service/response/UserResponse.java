package com.prography.assignment.api.user.service.response;

import com.prography.assignment.common.util.DateTimeUtil;
import com.prography.assignment.domain.user.model.User;

public record UserResponse(
        int id,
        int fakerId,
        String name,
        String email,
        String status,
        String createdAt,
        String updatedAt
) {
    public static UserResponse of(User user){

        return new UserResponse(
                user.getId(),
                user.getFakerId(),
                user.getName(),
                user.getEmail(),
                user.getStatus().name(),
                DateTimeUtil.format(user.getCreatedAt(), "yyyy-MM-dd HH:mm:ss"),
                DateTimeUtil.format(user.getUpdatedAt(), "yyyy-MM-dd HH:mm:ss")
        );
    }
}
