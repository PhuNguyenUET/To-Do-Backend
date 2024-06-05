package com.skyline.todo.repository;

import com.skyline.todo.model.scheduledTask.ScheduledTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledTaskRepository extends JpaRepository<ScheduledTask, Integer> {
    List<ScheduledTask> findByUserEmail(String userEmail);
}
