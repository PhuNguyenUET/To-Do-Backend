package com.skyline.todo.repository;

import com.skyline.todo.model.sampleTask.SampleTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleTaskRepository extends JpaRepository<SampleTask, Integer> {
    List<SampleTask> findByUserEmail(String userEmail);
}
