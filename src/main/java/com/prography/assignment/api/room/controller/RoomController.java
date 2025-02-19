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
import com.prography.assignment.api.room.service.response.RoomGetResponse;
import com.prography.assignment.api.room.service.response.RoomWithDateResponse;
import com.prography.assignment.common.code.CommonSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ApiResponse<Void> createRoom(
            @RequestBody RoomPostRequest request
    ){
        roomService.createRoom(RoomPostCommand.of(request));
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }

    @GetMapping
    public ApiResponse<RoomGetResponse> getAllRooms(
            @RequestParam final int size,
            @RequestParam final int page
    ){
        RoomGetResponse response = roomService.getAllRooms(size, page);

        return ApiResponse.onSuccess(CommonSuccessCode.OK, response);
    }

    @GetMapping("/{roomId}")
    public ApiResponse<RoomWithDateResponse> getRoom(
            @PathVariable("roomId") final Integer roomId
    ){
        RoomWithDateResponse response = roomService.getRoom(roomId);

        return ApiResponse.onSuccess(CommonSuccessCode.OK, response);
    }

    @PostMapping("/attention/{roomId}")
    public ApiResponse<Void> createAttendance(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody final RoomAttendPostRequest request
            )
    {
        roomService.attendRoom(RoomAttendPostCommand.of(request, roomId));

        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }

    @PostMapping("/out/{roomId}")
    public ApiResponse<Void> deleteAttendance(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody final RoomOutPostRequest request
    ){
        roomService.outRoom(RoomOutPostCommand.of(roomId, request));

        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }

    @PostMapping("/start/{roomId}")
    public ApiResponse<Void> startGame(
            @PathVariable("roomId") final Integer roomId,
            @RequestBody RoomStartPostRequest request
    ){
        return ApiResponse.onSuccess(CommonSuccessCode.OK);
    }
}
