package com.skyline.todo.exceptions.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(int id) {
        super("User with id " + id + " already exists.");
    }

    public UserAlreadyExistsException(String username) {
        super("User with email " + username + " already exists.");
    }
}
