package com.prography.assignment.api.common.init.service;

import com.prography.assignment.domain.room.repository.RoomRepository;
import com.prography.assignment.domain.user.repository.UserRepository;
import com.prography.assignment.domain.userroom.repository.UserRoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DatabaseTableInitializerImpl implements Initializer {

    private final UserRoomRepository userRoomRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    private final EntityManager entityManager;

    @Override
    public void initDatabase() {
        try {
            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE;").executeUpdate();

            userRoomRepository.truncate();
            roomRepository.truncate();
            userRepository.truncate();

            entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE;").executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Database initialization failed", e);
        }
    }
}
