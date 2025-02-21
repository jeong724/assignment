package com.prography.assignment.api.room;

import com.prography.assignment.api.room.controller.request.RoomAttendPostRequest;
import com.prography.assignment.api.room.controller.request.RoomOutPostRequest;
import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.command.RoomAttendPostCommand;
import com.prography.assignment.api.room.service.command.RoomOutPostCommand;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.user.UserSteps;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import com.prography.assignment.domain.room.repository.RoomRepository;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.repository.UserRepository;
import com.prography.assignment.domain.userroom.model.Team;
import com.prography.assignment.domain.userroom.model.UserRoom;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class OutRoomCommandTest {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoomRepository userRoomRepository;

    private User savedActiveUser;

    private Room savedRoom;

    @BeforeEach
    void setUp(){

        User userActive = UserSteps.유저_STATUS_ACTIVE_객체_생성();
        savedActiveUser = userRepository.save(userActive);

        RoomPostRequest request = RoomSteps.룸_생성_요청(savedActiveUser);
        roomService.createRoom(RoomPostCommand.of(request));
        savedRoom = roomRepository.findAll().get(0);
    }

    @Test
    @Transactional
    void 룸에서_나간다(){

        //given
        RoomOutPostRequest request = RoomSteps.룸_나가기_요청(savedActiveUser);
        RoomOutPostCommand command = RoomOutPostCommand.of(savedRoom.getId(), request);

        //when
        roomService.outRoom(command);

        //then
        assertThat(userRoomRepository.findByUserAndRoom(savedActiveUser,savedRoom)).isNull();

        Room room = roomRepository.findById(savedRoom.getId()).orElseThrow();
        assertThat(room.getStatus()).isEqualTo(RoomStatus.FINISH);

    }
}
