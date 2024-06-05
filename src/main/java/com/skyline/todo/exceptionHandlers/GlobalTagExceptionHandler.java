package com.skyline.todo.exceptionHandlers;

import com.skyline.todo.exceptions.tag.NoSuchTagException;
import com.skyline.todo.exceptions.tag.TagAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalTagExceptionHandler {
    @ExceptionHandler(value = NoSuchTagException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchTagException(NoSuchTagException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = TagAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleTagAlreadyExistsException(TagAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
