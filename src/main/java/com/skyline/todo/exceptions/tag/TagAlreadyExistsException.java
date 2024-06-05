package com.skyline.todo.exceptions.tag;

public class TagAlreadyExistsException extends RuntimeException{
    public TagAlreadyExistsException(int id) {
        super("Tag with id " + id + " already exists.");
    }

    public TagAlreadyExistsException(String name) {
        super("Tag with name " + name + " already exists.");
    }
}
