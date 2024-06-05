package com.skyline.todo.controller;

import com.skyline.todo.DTO.SampleScheduledTaskDTO;
import com.skyline.todo.DTO.ScheduledTaskDTO;
import com.skyline.todo.service.ScheduledTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduledTask")
@RequiredArgsConstructor
public class ScheduledTaskController {
    private final ScheduledTaskService scheduledTaskService;

    @PostMapping("/add")
    public ResponseEntity<ScheduledTaskDTO> addScheduledTask(@RequestBody @Valid ScheduledTaskDTO scheduledTask) {
        ScheduledTaskDTO task = scheduledTaskService.post(scheduledTask);

        return ResponseEntity.ok(task);
    }

    @PostMapping("/addSample")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<SampleScheduledTaskDTO> addSampleScheduledTask(@RequestBody @Valid SampleScheduledTaskDTO scheduledTask) {
        SampleScheduledTaskDTO task = scheduledTaskService.postSample(scheduledTask);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/updateSample")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<SampleScheduledTaskDTO> updateSampleScheduledTask(@RequestBody @Valid SampleScheduledTaskDTO scheduledTask, @RequestParam("id") int taskId) {
        SampleScheduledTaskDTO task = scheduledTaskService.updateSample(scheduledTask, taskId);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/update")
    public ResponseEntity<ScheduledTaskDTO> updateScheduledTask(@RequestBody @Valid ScheduledTaskDTO scheduledTask, @RequestParam("id") int taskId) {
        ScheduledTaskDTO task = scheduledTaskService.update(scheduledTask, taskId);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteScheduledTask(@RequestParam("id") int taskId) {
        scheduledTaskService.delete(taskId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ScheduledTaskDTO>> getAllScheduledTask(Authentication authentication) {
        List<ScheduledTaskDTO> scheduledTasks = scheduledTaskService.getAllScheduledTask(authentication);

        return ResponseEntity.ok(scheduledTasks);
    }
}
