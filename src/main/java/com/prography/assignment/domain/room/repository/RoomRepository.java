package com.prography.assignment.domain.room.repository;

import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Modifying
    @Query(value = "truncate table room", nativeQuery = true)
    void truncate();

    Page<Room> findAllByOrderByIdAsc(Pageable pageable);

}
