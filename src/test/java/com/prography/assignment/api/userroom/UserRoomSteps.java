package com.prography.assignment.api.userroom;

import com.prography.assignment.api.userroom.controller.request.ChangeTeamRequest;
import com.prography.assignment.domain.user.model.User;

public class UserRoomSteps {

    public static ChangeTeamRequest 팀_변경_요청(User user){
        return new ChangeTeamRequest(user.getId());
    }
}
