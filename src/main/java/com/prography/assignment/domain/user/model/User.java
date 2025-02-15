package com.prography.assignment.domain.user.model;

import com.prography.assignment.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer fakerId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Builder
    private User(String name, Integer fakerId, String email, UserStatus status) {
        this.name = name;
        this.fakerId = fakerId;
        this.email = email;
        this.status = status;
    }

    public static User create(String name, Integer fakerId, String email, UserStatus status) {
        return User.builder()
                .name(name)
                .fakerId(fakerId)
                .email(email)
                .status(status)
                .build();
    }

}
