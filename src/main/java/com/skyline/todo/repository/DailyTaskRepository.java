package com.skyline.todo.repository;

import com.skyline.todo.model.dailyTask.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyTaskRepository extends JpaRepository<DailyTask, Integer> {
    List<DailyTask> findByUserEmailAndSetDate(String userEmail, LocalDate date);
}
