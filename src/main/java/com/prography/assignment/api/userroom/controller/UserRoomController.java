package com.prography.assignment.api.userroom.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.userroom.controller.request.ChangeTeamRequest;
import com.prography.assignment.api.userroom.service.UserRoomService;
import com.prography.assignment.api.userroom.service.command.ChangeTeamCommand;
import com.prography.assignment.common.code.CommonSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TEAM API", description = "팀 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserRoomController {

    private final UserRoomService userRoomService;

    @Operation(
            summary = "팀 변경 API",
            description = "user가 현재 해당 방에 참가한 상태, 변경되려는 팀의 인원이 이미 해당 방 정원의 절반 미만이며 현재 방의 상태가 대기(WAIT) 상태일 때만 팀을 변경 가능합니다."
    )
    @PutMapping("/team/{roomId}")
    public ApiResponse<Void> changeTeam(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody final ChangeTeamRequest request
    ){
        userRoomService.changeTeam(ChangeTeamCommand.of(request, roomId));
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }
}
