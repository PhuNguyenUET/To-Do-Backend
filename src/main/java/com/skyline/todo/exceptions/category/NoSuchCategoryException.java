package com.skyline.todo.exceptions.category;

public class NoSuchCategoryException extends RuntimeException{
    public NoSuchCategoryException(int id) {
        super("No such category found with id " + id + ".");
    }
}
