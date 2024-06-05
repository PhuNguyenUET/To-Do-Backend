package com.skyline.todo.model.scheduledTask;

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
