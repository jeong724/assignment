package com.prography.assignment.api.userroom;

import com.prography.assignment.api.room.RoomSteps;
import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.user.UserSteps;
import com.prography.assignment.api.userroom.controller.request.ChangeTeamRequest;
import com.prography.assignment.api.userroom.service.UserRoomService;
import com.prography.assignment.api.userroom.service.command.ChangeTeamCommand;
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
public class ChangeTeamCommandTest {

    @Autowired
    UserRoomService userRoomService;

    @Autowired
    RoomService roomService;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoomRepository userRoomRepository;

    private User savedActiveUser;

    private UserRoom savedUserRoom;

    @BeforeEach
    void setUp(){
        User userActive = UserSteps.유저_STATUS_ACTIVE_객체_생성();

        savedActiveUser = userRepository.save(userActive);

        RoomPostRequest request = RoomSteps.룸_생성_요청(savedActiveUser);
        int roomId = roomService.createRoom(RoomPostCommand.of(request));
        Room room = roomRepository.findById(roomId).orElseThrow();

        savedUserRoom = userRoomRepository.findByUserAndRoom(savedActiveUser, room);
    }

    @Test
    @Transactional
    void 팀을_변경한다(){

        //given
        ChangeTeamRequest request = UserRoomSteps.팀_변경_요청(savedActiveUser);
        ChangeTeamCommand command = ChangeTeamCommand.of(request, savedActiveUser.getId());

        //when
        userRoomService.changeTeam(command);

        //then
        assertThat(savedUserRoom.getTeam()).isEqualTo(Team.BLUE);
    }
}
