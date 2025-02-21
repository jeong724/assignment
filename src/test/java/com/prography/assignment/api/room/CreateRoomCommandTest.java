package com.prography.assignment.api.room;

import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.user.UserSteps;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import com.prography.assignment.domain.room.repository.RoomRepository;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class CreateRoomCommandTest {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    UserRepository userRepository;

    private User savedActiveUser;

    @BeforeEach
    void setUp(){
        User userActive = UserSteps.유저_STATUS_ACTIVE_객체_생성();

        savedActiveUser = userRepository.save(userActive);
    }

    @Test
    @Transactional
    void 룸을_생성한다(){

        //given
        RoomPostRequest requestWait = RoomSteps.룸_생성_요청(savedActiveUser);

        //when
        int roomId = roomService.createRoom(RoomPostCommand.of(requestWait));

        //then
        Room savedRoom = roomRepository.findById(roomId).orElseThrow();
        assertThat(savedRoom.getHost()).isEqualTo(savedActiveUser);
        assertThat(savedRoom.getStatus()).isEqualTo(RoomStatus.WAIT);
    }
}
