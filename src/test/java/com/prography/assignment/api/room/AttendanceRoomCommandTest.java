package com.prography.assignment.api.room;

import com.prography.assignment.api.room.controller.request.RoomAttendPostRequest;
import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.command.RoomAttendPostCommand;
import com.prography.assignment.api.user.UserSteps;
import com.prography.assignment.domain.room.model.Room;
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
public class AttendanceRoomCommandTest {

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
        User userActive2 = UserSteps.유저_STATUS_ACTIVE_객체_생성2();

        savedActiveUser = userRepository.save(userActive);
        User savedActiveUser2 = userRepository.save(userActive2);

        Room room = RoomSteps.룸_객체_생성(savedActiveUser2);
        savedRoom = roomRepository.save(room);
    }

    @Test
    @Transactional
    void 룸에_참가한다(){

        //given
        RoomAttendPostRequest request = RoomSteps.룸_참가_요청(savedActiveUser);
        RoomAttendPostCommand command = RoomAttendPostCommand.of(request, savedRoom.getId());

        //when
        roomService.attendRoom(command);

        //then
        UserRoom userRoom = userRoomRepository.findByUserAndRoom(savedActiveUser, savedRoom);
        assertThat(userRoom.getTeam()).isEqualTo(Team.RED);
    }
}
