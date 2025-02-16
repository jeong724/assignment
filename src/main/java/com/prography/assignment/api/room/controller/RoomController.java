package com.prography.assignment.api.room.controller;

import com.prography.assignment.api.common.ApiResponse;
import com.prography.assignment.api.room.controller.request.RoomPostRequest;
import com.prography.assignment.api.room.service.RoomService;
import com.prography.assignment.api.room.service.command.RoomPostCommand;
import com.prography.assignment.api.room.service.response.RoomGetResponse;
import com.prography.assignment.common.code.CommonSuccessCode;
import lombok.Getter;
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
}
