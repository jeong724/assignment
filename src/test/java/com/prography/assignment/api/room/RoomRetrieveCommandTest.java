package com.prography.assignment.api.room;

import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.response.RoomGetResponse;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class RoomRetrieveCommandTest {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

    @Autowired
    UserRepository userRepository;

    private User savedActiveUser;
    private List<User> savedUsers;

    @BeforeEach
    void setUp(){
        User userActive = UserSteps.유저_STATUS_ACTIVE_객체_생성();
        User userActive2 = UserSteps.유저_STATUS_ACTIVE_객체_생성2();

        savedActiveUser = userRepository.save(userActive);
        savedActiveUser = userRepository.save(userActive2);

        savedUsers = userRepository.findAll();
    }

    @Test
    @Transactional
    void 룸을_전체_조회한다(){

        //given
        List<Room> savedRoomList = RoomSteps.룸_목록_생성(2, savedUsers);
        roomRepository.saveAll(savedRoomList);

        //when
        RoomGetResponse retrievedRooms = roomService.getAllRooms(2, 0);

        //then
        assertThat(retrievedRooms.roomList()).hasSize(2);
        assertThat(retrievedRooms.totalPages()).isEqualTo(1);
    }

    @Test
    @Transactional
    void 룸을_상세_조회한다(){

        //given
        Room savedRoom = RoomSteps.룸_객체_생성(savedActiveUser);
        roomRepository.save(savedRoom);

        //when
        roomService.getRoom(savedRoom.getId());

        //then
        assertThat(savedRoom.getStatus()).isEqualTo(RoomStatus.WAIT);
        assertThat(savedRoom.getHost()).isEqualTo(savedActiveUser);
    }
}
