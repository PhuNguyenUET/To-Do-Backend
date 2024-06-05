package com.skyline.todo.service;

import com.skyline.todo.DTO.DailyTaskDTO;
import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.scheduledTask.Tag;
import com.skyline.todo.modelMapper.ModelMapperConfig;
import com.skyline.todo.repository.DailyTaskRepository;
import com.skyline.todo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyTaskService {
    private final DailyTaskRepository dailyTaskRepository;
    private final TagRepository tagRepository;

    private final ModelMapper modelMapper;

    public DailyTaskDTO add(DailyTaskDTO dailyTask) {
        DailyTask task = this.modelMapper.map(dailyTask, DailyTask.class);
        return this.modelMapper.map(dailyTaskRepository.save(task), DailyTaskDTO.class);
    }

    public DailyTaskDTO update(DailyTaskDTO dailyTask, int id) {
        dailyTaskRepository.findById(id).orElseThrow();

        dailyTask.setId(id);
        DailyTask task = this.modelMapper.map(dailyTask, DailyTask.class);
        return this.modelMapper.map(dailyTaskRepository.save(task), DailyTaskDTO.class);
    }

    public List<DailyTaskDTO> getAllDailyTasksByDate(LocalDate date, Authentication authentication) {
        String username = authentication.getName();

        return dailyTaskRepository
                .findByUserEmailAndSetDate(username, date)
                .stream()
                .map(dailyTask -> this.modelMapper.map(dailyTask, DailyTaskDTO.class))
                .toList();
    }

    public boolean toggleCompleted(int id) {
        DailyTask dailyTask = dailyTaskRepository.findById(id).orElseThrow();
        dailyTask.setFinished(!dailyTask.isFinished());
        dailyTaskRepository.save(dailyTask);
        return dailyTask.isFinished();
    }

    public void markAllCompletedByDate(LocalDate date, Authentication authentication) {
        String username = authentication.getName();
        List<DailyTask> dailyTasks = dailyTaskRepository.findByUserEmailAndSetDate(username, date);

        for (DailyTask dailyTask : dailyTasks) {
            dailyTask.setFinished(true);
            dailyTaskRepository.save(dailyTask);
        }
    }

    public void delete(int id) {
        dailyTaskRepository.findById(id).orElseThrow();
        dailyTaskRepository.deleteById(id);
    }

    public List<DailyTaskDTO> getAllDailyTasksByDateAndTag(LocalDate date, int tagId, Authentication authentication) {
        Tag tag = tagRepository.findById(tagId).orElseThrow();
        String username = authentication.getName();
        List<DailyTask> dailyTasks = dailyTaskRepository.findByUserEmailAndSetDate(username, date);
        List<DailyTask> dailyTasksWithTag = new ArrayList<>();
        for(DailyTask dailyTask : dailyTasks) {
            if(dailyTask.getTags().contains(tag)) {
                dailyTasksWithTag.add(dailyTask);
            }
        }
        return dailyTasksWithTag
                .stream()
                .map(dailyTask -> this.modelMapper.map(dailyTask, DailyTaskDTO.class))
                .toList();
    }
}
