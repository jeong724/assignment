package com.prography.assignment.api.userroom.service;

import com.prography.assignment.api.room.service.RoomFinder;
import com.prography.assignment.api.user.service.UserFinder;
import com.prography.assignment.api.userroom.service.command.ChangeTeamCommand;
import com.prography.assignment.common.code.BusinessErrorCode;
import com.prography.assignment.common.exception.SpecificException;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import com.prography.assignment.domain.user.model.User;
import com.prography.assignment.domain.userroom.model.UserRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRoomService {

    private final UserFinder userFinder;
    private final RoomFinder roomFinder;
    private final UserRoomValidator userRoomValidator;
    private final UserRoomFinder userRoomFinder;

    @Transactional
    public void changeTeam(ChangeTeamCommand command){

        User user = userFinder.findById(command.userId())
                .orElseThrow(()-> new SpecificException(BusinessErrorCode.BAD_REQUEST));

        Room room = roomFinder.findRoom(command.roomId())
                .orElseThrow(()-> new SpecificException(BusinessErrorCode.BAD_REQUEST));

        if (!userRoomValidator.isUserInRoom(user, room)){
            throw new SpecificException(BusinessErrorCode.BAD_REQUEST);
        }

        UserRoom roomMember = userRoomFinder.findUserRoom(user, room);

        if (!validateChangeTeam(roomMember, room)){
            throw new SpecificException(BusinessErrorCode.BAD_REQUEST);
        }

        roomMember.changeTeam(roomMember);
    }

    private boolean validateChangeTeam(UserRoom roomMember, Room room) {

        // 반대편 팀이 가득 찼을때
        int oppositeTeamAttendance = userRoomValidator.countTeamMembers(room, roomMember.getOppositeTeam());
        if (oppositeTeamAttendance == room.getCapacity()/2){
            return false;
        }

        // 현재 방이 대기 상태일때
        if (room.getStatus() != RoomStatus.WAIT) {
            return false;
        }

        return true;

    }
}
