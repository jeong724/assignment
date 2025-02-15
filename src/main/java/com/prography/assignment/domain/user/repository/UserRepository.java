package com.prography.assignment.domain.user.repository;

import com.prography.assignment.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findAllByOrderByIdAsc(Pageable pageable);

    @Modifying
    @Query(value = "truncate table users", nativeQuery = true)
    void truncate();

}