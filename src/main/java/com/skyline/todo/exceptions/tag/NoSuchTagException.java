package com.skyline.todo.exceptions.tag;

public class NoSuchTagException extends RuntimeException{
    public NoSuchTagException(int id) {
        super("No such tag found with id " + id + ".");
    }
}
