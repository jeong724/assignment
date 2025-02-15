package com.prography.assignment.domain.room.repository;

import com.prography.assignment.domain.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
