package com.prography.assignment.api.userroom.service;

import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoomDeleter {

    private final UserRoomRepository userRoomRepository;

    public void deleteUserRoom(Room room){
        userRoomRepository.deleteByRoom(room);
    }

    public void deleteUser(User user){
        userRoomRepository.deleteByUser(user);
    }
}
