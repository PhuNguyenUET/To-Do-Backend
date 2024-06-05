package com.skyline.todo.controller;

import com.skyline.todo.DTO.DailyTaskDTO;
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
    public ResponseEntity<DailyTaskDTO> addDailyTask(@RequestBody @Valid DailyTaskDTO dailyTask) {
        DailyTaskDTO task = dailyTaskService.add(dailyTask);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/update")
    public ResponseEntity<DailyTaskDTO> updateDailyTask(@RequestBody @Valid DailyTaskDTO dailyTask, @RequestParam("id") int taskId) {
        DailyTaskDTO task = dailyTaskService.update(dailyTask, taskId);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDailyTask(@RequestParam("id") int taskId) {
        dailyTaskService.delete(taskId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DailyTaskDTO>> getAllDailTaskByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Authentication authentication) {
        List<DailyTaskDTO> dailyTasks = dailyTaskService.getAllDailyTasksByDate(date, authentication);
        return ResponseEntity.ok(dailyTasks);
    }

    @PostMapping("/toggleCompleted")
    public ResponseEntity<Boolean> toggleCompleted(@RequestParam("id") int taskId) {
        boolean completedState = dailyTaskService.toggleCompleted(taskId);
        return ResponseEntity.ok(completedState);
    }

    @PostMapping("/markAllCompleted")
    public ResponseEntity<?> markAllCompletedByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Authentication authentication) {
        dailyTaskService.markAllCompletedByDate(date, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllByTag")
    public ResponseEntity<List<DailyTaskDTO>> getAllDailyTaskByDateAndTag(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("tagId") int tagId,
            Authentication authentication
            ) {
        List<DailyTaskDTO> dailyTasks = dailyTaskService.getAllDailyTasksByDateAndTag(date, tagId, authentication);
        return ResponseEntity.ok(dailyTasks);
    }
}
