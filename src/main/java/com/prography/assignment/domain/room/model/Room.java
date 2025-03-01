package com.prography.assignment.domain.room.model;

import com.prography.assignment.domain.common.BaseTimeEntity;
import com.prography.assignment.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User host;

    @Builder
    private Room(Integer id, String title, RoomType roomType, RoomStatus status, User host) {
        this.id = id;
        this.title = title;
        this.roomType = roomType;
        this.status = status;
        this.host = host;
    }
    public static Room create(String title, RoomType roomType, RoomStatus status, User host) {
        return Room.builder()
                .title(title)
                .roomType(roomType)
                .status(status)
                .host(host)
                .build();
    }

    public int getCapacity() {
        return roomType.getCapacity();
    }

    public void changeRoomStatus(RoomStatus status){
        this.status = status;
    }
}
