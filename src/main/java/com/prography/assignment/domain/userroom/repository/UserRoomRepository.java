package com.prography.assignment.domain.userroom.repository;

import com.prography.assignment.domain.userroom.model.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {
}
