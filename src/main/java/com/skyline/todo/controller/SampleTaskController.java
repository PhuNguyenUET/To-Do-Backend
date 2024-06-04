package com.skyline.todo.controller;

import com.skyline.todo.model.sampleTask.SampleTask;
import com.skyline.todo.service.SampleTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sampleTask")
@RequiredArgsConstructor
public class SampleTaskController {
    private final SampleTaskService sampleTaskService;

    @PostMapping("/add")
    public ResponseEntity<SampleTask> addSampleTask(@RequestBody @Valid SampleTask sampleTask) {
        SampleTask task = sampleTaskService.post(sampleTask);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/update")
    public ResponseEntity<SampleTask> updateSampleTask(@RequestBody @Valid SampleTask sampleTask, @RequestParam("id") int taskId) {
        SampleTask task = sampleTaskService.update(sampleTask, taskId);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSampleTask(@RequestParam("id") int taskId) {
        sampleTaskService.delete(taskId);

        return ResponseEntity.ok("Sample task deleted successfully");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SampleTask>> getAllSampleTask(Authentication authentication) {
        List<SampleTask> sampleTasks = sampleTaskService.getAllSampleTask(authentication);

        return ResponseEntity.ok(sampleTasks);
    }
}
