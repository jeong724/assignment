package com.prography.assignment.domain.room.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomType {
      SINGLE(2), DOUBLE(4);

      private final int capacity;
}
