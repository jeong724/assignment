package com.prography.assignment.api.room;

import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.domain.room.model.Room;
import com.prography.assignment.domain.room.model.RoomStatus;
import com.prography.assignment.domain.room.model.RoomType;
import com.prography.assignment.domain.user.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoomSteps {

    public static RoomPostRequest 룸_생성_요청(User host){
        return new RoomPostRequest(host.getId(), RoomType.SINGLE.name(), "test room1");
    }

    public static Room 룸_객체_생성(User host){
        return Room.create("testRoom", RoomType.DOUBLE, RoomStatus.WAIT, host);
    }

    public static List<Room> 룸_목록_생성(int count, List<User> host) {
        return IntStream.range(0, count)
                .mapToObj(i -> Room.create("testRoom" + i, RoomType.DOUBLE, RoomStatus.WAIT, host.get(i)))
                .collect(Collectors.toList());
    }
}
