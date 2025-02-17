package com.prography.assignment.api.room.service.response;

import com.prography.assignment.domain.room.model.Room;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public record RoomGetResponse(
        int totalElements,
        int totalPages,
        List<RoomResponse> roomList
) {
    public static RoomGetResponse of(final Page<Room> rooms){
        return new RoomGetResponse(
                (int)rooms.getTotalElements(),
                rooms.getTotalPages(),
                rooms.getContent().stream()
                        .map(RoomResponse::of)
                        .collect(Collectors.toList())
        );
    }
}
