package com.prography.assignment.domain.userroom.repository;

import com.prography.assignment.domain.userroom.model.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {

    @Modifying
    @Query(value = "truncate table user_room", nativeQuery = true)
    void truncate();
}
