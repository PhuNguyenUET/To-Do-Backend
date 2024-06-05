package com.skyline.todo.exceptions.token;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String time) {
        super("This token has expired since " + time + ".");
    }
}
