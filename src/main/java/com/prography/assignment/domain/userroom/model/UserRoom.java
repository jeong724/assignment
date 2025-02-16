package com.prography.assignment.domain.userroom.model;

import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Team team;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Builder
    private UserRoom(Team team, User user, Room room) {
        this.team = team;
        this.user = user;
        this.room = room;
    }
    public static UserRoom create(Team team, User user, Room room) {
        return UserRoom.builder()
                .team(team)
                .user(user)
                .room(room)
                .build();

    }
}
