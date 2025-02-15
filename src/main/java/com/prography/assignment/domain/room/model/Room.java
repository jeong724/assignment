package com.prography.assignment.domain.room.model;

import com.prography.assignment.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Entity
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

}
