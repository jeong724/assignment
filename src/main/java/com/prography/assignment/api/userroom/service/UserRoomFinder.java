package com.prography.assignment.api.userroom.service;

import com.prography.assignment.api.user.service.UserFinder;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.userroom.model.UserRoom;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRoomFinder {

    private final UserRoomRepository userRoomRepository;

    public UserRoom findUserRoom(User user, Room room) {
        return userRoomRepository.findByUserAndRoom(user, room);
    }
}
