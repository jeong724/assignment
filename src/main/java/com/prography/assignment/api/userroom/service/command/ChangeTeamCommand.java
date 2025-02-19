package com.prography.assignment.api.userroom.service.command;

import com.prography.assignment.api.userroom.controller.request.ChangeTeamRequest;

public record ChangeTeamCommand(
        int userId,
        int roomId
) {
    public static ChangeTeamCommand of(ChangeTeamRequest request, int roomId){
        return new ChangeTeamCommand(request.userId(), roomId);
    }
}
