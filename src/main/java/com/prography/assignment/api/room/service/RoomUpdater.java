package com.prography.assignment.api.room.service;

import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomUpdater {

    private final RoomRepository roomRepository;

    protected void createRoom(Room room) {
        roomRepository.save(room);
    }
}
