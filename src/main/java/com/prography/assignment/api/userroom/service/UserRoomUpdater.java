package com.prography.assignment.api.userroom.service;

import com.prography.assignment.domain.userroom.model.UserRoom;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoomUpdater {

    private final UserRoomRepository userRoomRepository;

    public void save(UserRoom userRoom) {
        userRoomRepository.save(userRoom);

    }

}
