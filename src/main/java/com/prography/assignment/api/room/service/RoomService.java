package com.prography.assignment.api.room.service;

import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.user.service.UserFinder;
import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.BadRequestException;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.model.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final UserFinder userFinder;

    @Transactional
    public void createRoom(final RoomPostCommand command) {
        User host = userFinder.findById(command.userId())
                .orElseThrow(()-> new BadRequestException(BusinessErrorCode.BAD_REQUEST));

        if (!isHostActive(host)) {
            throw new BadRequestException(BusinessErrorCode.BAD_REQUEST);
        }



    }

    // 활성 상태아니라면 게임 시작 x
    private boolean isHostActive(final User host) {
        return host.getStatus() == UserStatus.ACTIVE;
    }

    //
    private boolean isAlreadyJoined(final User host) {

    }
}
