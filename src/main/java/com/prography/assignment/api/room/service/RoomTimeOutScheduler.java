package com.prography.assignment.api.room.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RoomTimeOutScheduler {

    private final RoomFinisher roomFinisher;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final int TIME_OUT_SECONDS = 60;

    @Async
    public void finish(int roomId) {

        // 60초 후 finishRoom 실행 예약
        scheduler.schedule(() -> roomFinisher.finishRoom(roomId), TIME_OUT_SECONDS, TimeUnit.SECONDS);
    }
}
