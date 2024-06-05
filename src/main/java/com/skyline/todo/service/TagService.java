package com.skyline.todo.service;

import com.skyline.todo.DTO.TagDTO;
import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.scheduledTask.Tag;
import com.skyline.todo.repository.DailyTaskRepository;
import com.skyline.todo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final DailyTaskRepository dailyTaskRepository;

    private final ModelMapper modelMapper;

    public TagDTO post(TagDTO tag) {
        Tag t = this.modelMapper.map(tag, Tag.class);
        return this.modelMapper.map(tagRepository.save(t), TagDTO.class);
    }

    public TagDTO update(TagDTO tag, int id) {
        tagRepository.findById(id).orElseThrow();

        tag.setId(id);
        Tag t = this.modelMapper.map(tag, Tag.class);
        return this.modelMapper.map(tagRepository.save(t), TagDTO.class);
    }

    public void delete(int id) {
        tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
    }

    public List<TagDTO> getDisplayTag(Authentication authentication) {
        String username = authentication.getName();

        List<Tag> tagsByUser = tagRepository.findFirst3ByUserEmail(username);
        List<Tag> sampleTags = tagRepository.findFirst3ByUserEmail(null);

        tagsByUser.addAll(sampleTags);
        return tagsByUser
                .stream()
                .map(tag -> this.modelMapper.map(tag, TagDTO.class))
                .toList();
    }

    public List<TagDTO> getAllTag(Authentication authentication) {
        String username = authentication.getName();

        return tagRepository
                .findAllByUserEmail(username)
                .stream()
                .map(tag -> this.modelMapper.map(tag, TagDTO.class))
                .toList();
    }

    public List<TagDTO> getAllTagInDay(Authentication authentication, LocalDate date) {
        String username = authentication.getName();

        List<DailyTask> dailyTasks = dailyTaskRepository.findByUserEmailAndSetDate(username, date);
        Set<Tag> dailyTags = new HashSet<>();

        for(DailyTask dailyTask : dailyTasks) {
            dailyTags.addAll(dailyTask.getTags());
        }

        return dailyTags
                .stream()
                .map(tag -> this.modelMapper.map(tag, TagDTO.class))
                .toList();
    }
}
