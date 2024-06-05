package com.skyline.todo.exceptionHandlers;

import com.skyline.todo.exceptions.dailyTask.NoSuchDailyTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDailyTaskExceptionHandler {
    @ExceptionHandler(value = NoSuchDailyTaskException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchDailyTaskException(NoSuchDailyTaskException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
