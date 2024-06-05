package com.skyline.todo.exceptionHandlers;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime localDateTime;
    private String message;

    public ErrorResponse(String message) {
        super();
        this.localDateTime = LocalDateTime.now();
        this.message = message;
    }
}

