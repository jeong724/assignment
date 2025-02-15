package com.prography.assignment.domain.user.repository;

import com.prography.assignment.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
