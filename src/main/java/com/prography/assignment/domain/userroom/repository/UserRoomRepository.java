package com.prography.assignment.domain.userroom.repository;

import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.userroom.model.Team;
import com.prography.assignment.domain.userroom.model.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRoomRepository extends JpaRepository<UserRoom, Integer> {

    @Modifying
    @Query(value = "truncate table user_room", nativeQuery = true)
    void truncate();

    boolean existsByUser(User user);

    int countByRoom(Room room);

    int countByRoomAndTeam(Room room, Team team);

    boolean existsUserRoomByUserAndRoom(User user, Room room);

    void deleteByRoom(Room room);

    void deleteByUser(User user);

}
