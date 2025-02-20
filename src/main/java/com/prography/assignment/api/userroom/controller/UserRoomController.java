package com.prography.assignment.api.userroom.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.userroom.controller.request.ChangeTeamRequest;
import com.prography.assignment.api.userroom.service.UserRoomService;
import com.prography.assignment.api.userroom.service.command.ChangeTeamCommand;
import com.prography.assignment.common.code.CommonSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserRoomController {

    private final UserRoomService userRoomService;

    @PutMapping("/team/{roomId}")
    public ApiResponse<Void> changeTeam(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody final ChangeTeamRequest request
    ){
        userRoomService.changeTeam(ChangeTeamCommand.of(request, roomId));
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }
}
