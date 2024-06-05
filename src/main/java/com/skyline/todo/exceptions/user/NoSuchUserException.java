package com.skyline.todo.exceptions.user;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(int id) {
        super("No such user found with id " + id + ".");
    }

    public NoSuchUserException(String userEmail) {
        super("No such user found with email " + userEmail + ".");
    }
}
