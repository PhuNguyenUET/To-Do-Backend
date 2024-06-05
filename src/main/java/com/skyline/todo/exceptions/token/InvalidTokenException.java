package com.skyline.todo.exceptions.token;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("This token is invalid and cannot be trusted");
    }
}
