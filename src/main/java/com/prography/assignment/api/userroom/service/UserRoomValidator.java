package com.prography.assignment.api.userroom.service;

import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomType;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.userroom.model.Team;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoomValidator {

    private final UserRoomRepository userRoomRepository;

    public boolean userRoomExists(User user) {
        return userRoomRepository.existsByUser(user);
    }

    public int countUser(Room room){
        return userRoomRepository.countByRoom(room);
    }

    public int countRedTeam(Room room){
        return userRoomRepository.countByRoomAndTeam(room, Team.RED);
    }

}
