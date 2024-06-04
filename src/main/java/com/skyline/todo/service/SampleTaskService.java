package com.skyline.todo.service;

import com.skyline.todo.model.sampleTask.SampleTask;
import com.skyline.todo.repository.DailyTaskRepository;
import com.skyline.todo.repository.SampleTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleTaskService {
    private final SampleTaskRepository sampleTaskRepository;

    public SampleTask post(SampleTask sampleTask) {
        return sampleTaskRepository.save(sampleTask);
    }

    public SampleTask update(SampleTask sampleTask, int id) {
        Optional<SampleTask> sampleTaskOptional = sampleTaskRepository.findById(id);

        if(sampleTaskOptional.isEmpty()) {
            //TODO: Throw custom exception
        }

        sampleTask.setId(id);
        return sampleTaskRepository.save(sampleTask);
    }

    public List<SampleTask> getAllSampleTask(Authentication authentication) {
        String username = authentication.getName();

        return sampleTaskRepository.findByUserEmail(username);
    }

    public void delete(int id) {
        Optional<SampleTask> sampleTaskOptional = sampleTaskRepository.findById(id);
        if(sampleTaskOptional.isEmpty()) {
            //TODO: Throw custom exception
        }
        sampleTaskRepository.deleteById(id);
    }
}
