package com.skyline.todo.service;

import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.sampleTask.Tag;
import com.skyline.todo.repository.DailyTaskRepository;
import com.skyline.todo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final DailyTaskRepository dailyTaskRepository;

    public Tag post(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(Tag tag, int id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);

        if(tagOptional.isEmpty()) {
            //TODO: Throw custom exception
        }

        tag.setId(id);
        return tagRepository.save(tag);
    }

    public void delete(int id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);

        if(tagOptional.isEmpty()) {
            //TODO: Throw custom exception
        }

        tagRepository.deleteById(id);
    }

    public List<Tag> getAllTag(Authentication authentication) {
        String username = authentication.getName();

        List<Tag> tagsByUser = tagRepository.findFirst3ByUserEmail(username);
        List<Tag> sampleTags = tagRepository.findFirst3ByUserEmail(null);

        tagsByUser.addAll(sampleTags);
        return tagsByUser;
    }

    public List<Tag> getAllTagInDay(Authentication authentication, LocalDate date) {
        String username = authentication.getName();

        List<DailyTask> dailyTasks = dailyTaskRepository.findByUserEmailAndSetDate(username, date);
        Set<Tag> dailyTags = new HashSet<>();

        for(DailyTask dailyTask : dailyTasks) {
            dailyTags.addAll(dailyTask.getTags());
        }

        return dailyTags.stream().toList();
    }
}
