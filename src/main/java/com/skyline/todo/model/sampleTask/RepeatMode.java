package com.skyline.todo.model.sampleTask;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RepeatMode {
    NONE,
    DAILY,
    WEEKLY,
    MONTHLY,
}
