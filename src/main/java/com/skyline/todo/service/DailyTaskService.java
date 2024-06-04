package com.skyline.todo.service;

import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.sampleTask.Tag;
import com.skyline.todo.repository.DailyTaskRepository;
import com.skyline.todo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyTaskService {
    private final DailyTaskRepository dailyTaskRepository;
    private final TagRepository tagRepository;

    public DailyTask add(DailyTask dailyTask) {
        return dailyTaskRepository.save(dailyTask);
    }

    public DailyTask update(DailyTask dailyTask, int id) {
        Optional<DailyTask> daily = dailyTaskRepository.findById(id);
        if(daily.isEmpty()) {
            //TODO: Throw custom exception
        }
        dailyTask.setId(id);
        return dailyTaskRepository.save(dailyTask);
    }

    public List<DailyTask> getAllDailyTasksByDate(LocalDate date, Authentication authentication) {
        String username = authentication.getName();

        return dailyTaskRepository.findByUserEmailAndSetDate(username, date);
    }

    public boolean toggleCompleted(int id) {
        Optional<DailyTask> daily = dailyTaskRepository.findById(id);
        if(daily.isEmpty()) {
            //TODO: Throw custom exception
        }

        DailyTask dailyTask = daily.get();
        dailyTask.setFinished(!dailyTask.isFinished());
        dailyTaskRepository.save(dailyTask);
        return dailyTask.isFinished();
    }

    public void markAllCompletedByDate(LocalDate date, Authentication authentication) {
        List<DailyTask> dailyTasks = getAllDailyTasksByDate(date, authentication);

        for(DailyTask dailyTask : dailyTasks) {
            dailyTask.setFinished(true);
            dailyTaskRepository.save(dailyTask);
        }
    }

    public void delete(int id) {
        Optional<DailyTask> daily = dailyTaskRepository.findById(id);
        if(daily.isEmpty()) {
            //TODO: Throw custom exception
        }
        dailyTaskRepository.deleteById(id);
    }

    public List<DailyTask> getAllDailyTasksByDateAndTag(LocalDate date, int tagId, Authentication authentication) {
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if(tagOptional.isEmpty()) {
            //TODO: Throw custom exception
        }
        Tag tag = tagOptional.get();
        List<DailyTask> dailyTasks = getAllDailyTasksByDate(date, authentication);
        List<DailyTask> tasksInTag = tag.getDailyTasks();
        dailyTasks.retainAll(tasksInTag);
        return dailyTasks;
    }
}
