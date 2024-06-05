package com.skyline.todo.exceptions.category;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(int id) {
        super("Category with id " + id + " already exists.");
    }

    public CategoryAlreadyExistsException(String name) {
        super("Category with name " + name + " already exists.");
    }
}
