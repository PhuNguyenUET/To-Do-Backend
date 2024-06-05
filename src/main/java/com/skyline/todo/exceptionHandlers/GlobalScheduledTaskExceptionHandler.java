package com.skyline.todo.exceptionHandlers;

import com.skyline.todo.exceptions.scheduledTask.NoSuchScheduledTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalScheduledTaskExceptionHandler {
    @ExceptionHandler(value = NoSuchScheduledTaskException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchScheduledTaskException(NoSuchScheduledTaskException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
