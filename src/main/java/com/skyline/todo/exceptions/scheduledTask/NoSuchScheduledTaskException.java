package com.skyline.todo.exceptions.scheduledTask;

public class NoSuchScheduledTaskException extends RuntimeException{
    public NoSuchScheduledTaskException(int id) {
        super("No such scheduled task found with id " + id + ".");
    }
}
