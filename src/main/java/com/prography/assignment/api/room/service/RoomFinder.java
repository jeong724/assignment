package com.prography.assignment.api.room.service;

import com.prography.assignment.api.room.service.response.RoomGetResponse;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomFinder {

    private final RoomRepository roomRepository;

    public Page<Room> findRooms(int size, int page){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return roomRepository.findAllByOrderByIdAsc(pageable);
    }
}
