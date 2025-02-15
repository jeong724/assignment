package com.prography.assignment.api.user.service.response;

import com.prography.assignment.domain.user.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public record UserGetResponse(
        int totalElements,
        int totalPages,
        List<UserResponse> users
) {
	public static UserGetResponse of(final Page<User> users){
		return new UserGetResponse(
				(int)users.getTotalElements(),
				users.getTotalPages(),
				users.getContent()
						.stream()
						.map(UserResponse::of)
						.collect(Collectors.toList())
		);
	}
}

