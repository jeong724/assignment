package com.prography.assignment.api.room;

import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.controller.request.RoomStartPostRequest;
import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.command.RoomAttendPostCommand;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.room.service.command.RoomStartPostCommand;
import com.prography.assignment.api.user.UserSteps;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import com.prography.assignment.domain.room.repository.RoomRepository;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.repository.UserRepository;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class StartRoomCommandTest {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoomRepository userRoomRepository;

    private User host;

    private Room savedRoom;

    private int roomId;

    @BeforeEach
    void setUp(){

        User userActive = UserSteps.유저_STATUS_ACTIVE_객체_생성();
        User userActive2 = UserSteps.유저_STATUS_ACTIVE_객체_생성2();

        host = userRepository.save(userActive);
        User user2 = userRepository.save(userActive2);

        RoomPostRequest request = RoomSteps.룸_생성_요청(host);
        int roomId = roomService.createRoom(RoomPostCommand.of(request));
        savedRoom = roomRepository.findById(roomId).orElseThrow();

        roomService.attendRoom(RoomAttendPostCommand.of(RoomSteps.룸_참가_요청(user2), roomId));
    }

    @AfterEach
    void tearDown() {
        userRoomRepository.deleteAll();
        roomRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void 게임을_시작한다()  throws InterruptedException{

        //given
        RoomStartPostRequest request = RoomSteps.게임_시작_요청(host);
        RoomStartPostCommand command = RoomStartPostCommand.of(request, savedRoom.getId());

        //when
        roomService.startRoom(command);

        //then
        Room progressdRoom = roomRepository.findById(savedRoom.getId()).orElseThrow();
        assertThat(progressdRoom.getStatus()).isEqualTo(RoomStatus.PROGRESS);

        Thread.sleep(70000);

        Room finishedRoom = roomRepository.findById(savedRoom.getId()).orElseThrow();
        assertThat(finishedRoom.getStatus()).isEqualTo(RoomStatus.FINISH);
    }


}
