package com.prography.assignment.domain.room.repository;

import com.prography.assignment.domain.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Modifying
    @Query(value = "truncate table room", nativeQuery = true)
    void truncate();
}
