package com.skyline.todo.scheduledTask;

import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.sampleTask.RepeatMode;
import com.skyline.todo.model.sampleTask.SampleTask;
import com.skyline.todo.repository.DailyTaskRepository;
import com.skyline.todo.repository.SampleTaskRepository;
import com.skyline.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    final DailyTaskRepository dailyTaskRepository;
    final SampleTaskRepository sampleTaskRepository;

    final LocalDate date = LocalDate.now();

    @Scheduled(cron = "@daily")
    public void generateDailyTasks() {
        List<SampleTask> sampleTasks = sampleTaskRepository.findAll();
        String dayOfWeek = date.getDayOfWeek().name();
        String dayOfMonth = String.valueOf(date.getDayOfMonth());
        for(SampleTask sampleTask : sampleTasks) {
            if(sampleTask.getRepeatMode() == RepeatMode.DAILY) {
                generateDailyTasksBySampleTasks(sampleTask);
            } else if (sampleTask.getRepeatMode() == RepeatMode.WEEKLY && sampleTask.getRepeatTime().contains(dayOfWeek)) {
                generateDailyTasksBySampleTasks(sampleTask);
            } else if (sampleTask.getRepeatMode() == RepeatMode.MONTHLY && sampleTask.getRepeatTime().contains(dayOfMonth)) {
                generateDailyTasksBySampleTasks(sampleTask);
            }
        }
    }

    private void generateDailyTasksBySampleTasks(SampleTask sampleTask) {
        DailyTask dailyTask = DailyTask.builder()
                .user(sampleTask.getUser())
                .setDate(date)
                .tags(sampleTask.getTags())
                .isFinished(false)
                .build();
        dailyTaskRepository.save(dailyTask);
    }
}
