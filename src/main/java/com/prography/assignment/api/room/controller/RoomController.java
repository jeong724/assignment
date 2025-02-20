package com.prography.assignment.api.room.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.room.controller.request.RoomAttendPostRequest;
import com.prography.assignment.api.room.controller.request.RoomOutPostRequest;
import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.controller.request.RoomStartPostRequest;
import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.command.RoomAttendPostCommand;
import com.prography.assignment.api.room.service.command.RoomOutPostCommand;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.room.service.command.RoomStartPostCommand;
import com.prography.assignment.api.room.service.response.RoomGetResponse;
import com.prography.assignment.api.room.service.response.RoomWithDateResponse;
import com.prography.assignment.common.code.CommonSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ROOM API", description = "게임 방 관련 API 입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @Operation(
            summary = "방 생성 API",
            description = "방을 생성하려고 하는 user의 상태가 활성(ACTIVE)상태이며 방을 생성하는 user가 참여한 방이 없을떄 방을 생성할 수 있습니다. "
    )
    @PostMapping
    public ApiResponse<Void> createRoom(
            @RequestBody RoomPostRequest request
    ){
        roomService.createRoom(RoomPostCommand.of(request));
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }

    @Operation(
            summary = "방 전체 조회 API",
            description = "id 기준 오름차순으로 모든 방에 대한 데이터를 반환합니다. 페이징 처리를 위해 size와 page를 넘겨주세요"
    )
    @GetMapping
    public ApiResponse<RoomGetResponse> getAllRooms(
            @RequestParam final int size,
            @RequestParam final int page
    ){
        RoomGetResponse response = roomService.getAllRooms(size, page);

        return ApiResponse.onSuccess(CommonSuccessCode.OK, response);
    }

    @Operation(
            summary = "방 상세 조회 API",
            description = "roomId에 해당하는 방에 대한 상세 조회를 합니다."
    )
    @GetMapping("/{roomId}")
    public ApiResponse<RoomWithDateResponse> getRoom(
            @PathVariable("roomId") final Integer roomId
    ){
        RoomWithDateResponse response = roomService.getRoom(roomId);

        return ApiResponse.onSuccess(CommonSuccessCode.OK, response);
    }

    @Operation(
            summary = "방 참가 API",
            description = "대기(WAIT) 상태인 방에만 참가가 가능, user의 상태가 활성(ACTIVE)상태이며 방을 생성하는 user가 참여한 방이 없을떄 참여가능합니다."
    )
    @PostMapping("/attention/{roomId}")
    public ApiResponse<Void> createAttendance(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody final RoomAttendPostRequest request
            )
    {
        roomService.attendRoom(RoomAttendPostCommand.of(request, roomId));

        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }

    @Operation(
            summary = "방 나가기 API",
            description = "이미 시작(PROGRESS) 상태인 방이거나 끝난(FINISH) 상태의 방은 나갈 수 없으며 호스트가 방을 나가게 되면 방에 있던 모든 사람도 해당 방에서 나가게 됩니다."
    )
    @PostMapping("/out/{roomId}")
    public ApiResponse<Void> deleteAttendance(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody final RoomOutPostRequest request
    ){
        roomService.outRoom(RoomOutPostCommand.of(roomId, request));

        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }

    @Operation(
            summary = "게임시작 API",
            description = "user가 호스트일때만 게임 시작 가능, 방 정원이 방의 타입에 맞게 모두 꽉 찬 상태이며 현재 방의 상태가 대기(WAIT) 상태일 때만 게임을 시작할 수 있습니다."
    )
    @PutMapping("/start/{roomId}")
    public ApiResponse<Void> startGame(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody final RoomStartPostRequest request
    ){
        roomService.startRoom(RoomStartPostCommand.of(request, roomId));
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }
}
