package com.skyline.todo.service;

import com.skyline.todo.DTO.DailyTaskDTO;
import com.skyline.todo.DTO.SampleScheduledTaskDTO;
import com.skyline.todo.DTO.ScheduledTaskDTO;
import com.skyline.todo.model.scheduledTask.ScheduledTask;
import com.skyline.todo.repository.ScheduledTaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final ScheduledTaskRepository scheduledTaskRepository;

    private final ModelMapper modelMapper;

    public ScheduledTaskDTO post(ScheduledTaskDTO scheduledTask) {
        ScheduledTask task = this.modelMapper.map(scheduledTask, ScheduledTask.class);
        return this.modelMapper.map(scheduledTaskRepository.save(task), ScheduledTaskDTO.class);
    }

    public SampleScheduledTaskDTO postSample(SampleScheduledTaskDTO scheduledTask) {
        ScheduledTask task = this.modelMapper.map(scheduledTask, ScheduledTask.class);
        return this.modelMapper.map(scheduledTaskRepository.save(task), SampleScheduledTaskDTO.class);
    }

    public ScheduledTaskDTO update(ScheduledTaskDTO scheduledTask, int id) {
        scheduledTaskRepository.findById(id).orElseThrow();

        scheduledTask.setId(id);
        ScheduledTask task = this.modelMapper.map(scheduledTask, ScheduledTask.class);
        return this.modelMapper.map(scheduledTaskRepository.save(task), ScheduledTaskDTO.class);
    }

    public SampleScheduledTaskDTO updateSample(SampleScheduledTaskDTO scheduledTask, int id) {
        scheduledTaskRepository.findById(id).orElseThrow();

        scheduledTask.setId(id);
        ScheduledTask task = this.modelMapper.map(scheduledTask, ScheduledTask.class);
        return this.modelMapper.map(scheduledTaskRepository.save(task), SampleScheduledTaskDTO.class);
    }

    public List<ScheduledTaskDTO> getAllScheduledTask(Authentication authentication) {
        String username = authentication.getName();

        return scheduledTaskRepository
                .findByUserEmail(username)
                .stream()
                .map(scheduledTask -> this.modelMapper.map(scheduledTask, ScheduledTaskDTO.class))
                .toList();
    }

    public void delete(int id) {
        scheduledTaskRepository.findById(id).orElseThrow();
        scheduledTaskRepository.deleteById(id);
    }
}
