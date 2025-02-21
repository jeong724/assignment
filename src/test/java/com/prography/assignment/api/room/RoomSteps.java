package com.prography.assignment.api.room;

import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomType;
import com.prography.assignment.domain.user.model.User;

public class RoomSteps {

    public static RoomPostRequest 룸_생성_요청(User host){
        return new RoomPostRequest(host.getId(), RoomType.SINGLE.name(), "test room1");
    }
}
