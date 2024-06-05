package com.skyline.todo.exceptions.dailyTask;

public class NoSuchDailyTaskException extends RuntimeException {
    public NoSuchDailyTaskException(int id) {
        super("No such daily task found with id " + id + ".");
    }
}
