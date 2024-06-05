package com.skyline.todo.exceptionHandlers;

import com.skyline.todo.exceptions.category.CategoryAlreadyExistsException;
import com.skyline.todo.exceptions.category.NoSuchCategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalCategoryExceptionHandler {
    @ExceptionHandler(value = NoSuchCategoryException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchCategoryException(NoSuchCategoryException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(value = CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
