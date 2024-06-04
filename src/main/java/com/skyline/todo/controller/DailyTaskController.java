package com.skyline.todo.controller;

import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.service.DailyTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dailyTask")
@RequiredArgsConstructor
public class DailyTaskController {
    private final DailyTaskService dailyTaskService;

    @PostMapping("/add")
    public ResponseEntity<DailyTask> addDailyTask(@RequestBody @Valid DailyTask dailyTask) {
        DailyTask task = dailyTaskService.add(dailyTask);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/update")
    public ResponseEntity<DailyTask> updateDailyTask(@RequestBody @Valid DailyTask dailyTask, @RequestParam("id") int taskId) {
        DailyTask task = dailyTaskService.update(dailyTask, taskId);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDailyTask(@RequestParam("id") int taskId) {
        dailyTaskService.delete(taskId);
        return ResponseEntity.ok("Daily task deleted successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DailyTask>> getAllDailTaskByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Authentication authentication) {
        List<DailyTask> dailyTasks = dailyTaskService.getAllDailyTasksByDate(date, authentication);
        return ResponseEntity.ok(dailyTasks);
    }

    @GetMapping("/toggleCompleted")
    public ResponseEntity<Boolean> toggleCompleted(@RequestParam("id") int taskId) {
        boolean completedState = dailyTaskService.toggleCompleted(taskId);
        return ResponseEntity.ok(completedState);
    }

    @GetMapping("/markAllCompleted")
    public ResponseEntity<String> markAllCompletedByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Authentication authentication) {
        dailyTaskService.markAllCompletedByDate(date, authentication);
        return ResponseEntity.ok("Operation completed");
    }

    @GetMapping("/getAllByTag")
    public ResponseEntity<List<DailyTask>> getAllDailyTaskByDateAndTag(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("tagId") int tagId,
            Authentication authentication
            ) {
        List<DailyTask> dailyTasks = dailyTaskService.getAllDailyTasksByDateAndTag(date, tagId, authentication);
        return ResponseEntity.ok(dailyTasks);
    }
}
