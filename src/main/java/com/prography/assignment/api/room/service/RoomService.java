package com.prography.assignment.api.room.service;

import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.user.service.UserFinder;
import com.prography.assignment.api.userroom.service.UserRoomValidator;
import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.BadRequestException;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import com.prography.assignment.domain.room.model.RoomType;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.user.model.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final UserFinder userFinder;
    private final UserRoomValidator userRoomValidator;
    private final RoomUpdater roomUpdater;

    @Transactional
    public void createRoom(final RoomPostCommand command) {
        User host = userFinder.findById(command.userId())
                .orElseThrow(()-> new BadRequestException(BusinessErrorCode.BAD_REQUEST));

        if(!validateCreateRoom(host))
            throw new BadRequestException(BusinessErrorCode.BAD_REQUEST);

        roomUpdater.save(Room.create(command.title(), RoomType.valueOf(command.roomType()), RoomStatus.WAIT));
    }

    private boolean validateCreateRoom(final User host) {

        // 활성 상태아니라면 룸 생성 x
        if (!(host.getStatus() == UserStatus.ACTIVE))
            return false;

        //유저가 다른 룸에 존재하면 룸 생성x
        if (userRoomValidator.userRoomExists(host))
            return false;

        return true;
    }


}
