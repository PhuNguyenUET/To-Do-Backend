package com.skyline.todo.springScheduledTask;

import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.scheduledTask.RepeatMode;
import com.skyline.todo.model.scheduledTask.ScheduledTask;
import com.skyline.todo.repository.DailyTaskRepository;
import com.skyline.todo.repository.ScheduledTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringScheduledTasks {
    final DailyTaskRepository dailyTaskRepository;
    final ScheduledTaskRepository scheduledTaskRepository;

    final LocalDate date = LocalDate.now();

    @Scheduled(cron = "@daily")
    public void generateDailyTasks() {
        List<ScheduledTask> scheduledTasks = scheduledTaskRepository.findAll();
        String dayOfWeek = date.getDayOfWeek().name();
        String dayOfMonth = String.valueOf(date.getDayOfMonth());
        for(ScheduledTask scheduledTask : scheduledTasks) {
            if(scheduledTask.getRepeatMode() == RepeatMode.DAILY) {
                generateDailyTasksBySampleTasks(scheduledTask);
            } else if (scheduledTask.getRepeatMode() == RepeatMode.WEEKLY && scheduledTask.getRepeatTime().contains(dayOfWeek)) {
                generateDailyTasksBySampleTasks(scheduledTask);
            } else if (scheduledTask.getRepeatMode() == RepeatMode.MONTHLY && scheduledTask.getRepeatTime().contains(dayOfMonth)) {
                generateDailyTasksBySampleTasks(scheduledTask);
            }
        }
    }

    private void generateDailyTasksBySampleTasks(ScheduledTask scheduledTask) {
        DailyTask dailyTask = DailyTask.builder()
                .user(scheduledTask.getUser())
                .setDate(date)
                .tags(scheduledTask.getTags())
                .isFinished(false)
                .build();
        dailyTaskRepository.save(dailyTask);
    }
}
