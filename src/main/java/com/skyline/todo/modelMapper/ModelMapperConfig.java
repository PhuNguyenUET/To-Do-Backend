package com.skyline.todo.modelMapper;

import com.skyline.todo.DTO.DailyTaskDTO;
import com.skyline.todo.DTO.SampleScheduledTaskDTO;
import com.skyline.todo.DTO.ScheduledTaskDTO;
import com.skyline.todo.DTO.TagDTO;
import com.skyline.todo.exceptions.category.NoSuchCategoryException;
import com.skyline.todo.exceptions.tag.NoSuchTagException;
import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.scheduledTask.Category;
import com.skyline.todo.model.scheduledTask.ScheduledTask;
import com.skyline.todo.model.scheduledTask.Tag;
import com.skyline.todo.repository.CategoryRepository;
import com.skyline.todo.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ModelMapperConfig {
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<DailyTask, DailyTaskDTO> dailyTaskDTOConverter = new Converter<DailyTask, DailyTaskDTO>() {
            @Override
            public DailyTaskDTO convert(MappingContext<DailyTask, DailyTaskDTO> context) {
                DailyTask dailyTask = context.getSource();
                return DailyTaskDTO.builder()
                        .id(dailyTask.getId())
                        .name(dailyTask.getName())
                        .setDate(dailyTask.getSetDate())
                        .tags(dailyTask.getTags().stream().map(Tag::getId).toList())
                        .isFinished(dailyTask.isFinished())
                        .build();
            }
        };

        modelMapper.addConverter(dailyTaskDTOConverter);

        Converter<DailyTaskDTO, DailyTask> dailyTaskConverter = new Converter<DailyTaskDTO, DailyTask>() {
            @Override
            public DailyTask convert(MappingContext<DailyTaskDTO, DailyTask> context) {
                DailyTaskDTO dailyTaskDTO = context.getSource();
                return DailyTask.builder()
                        .id(dailyTaskDTO.getId())
                        .name(dailyTaskDTO.getName())
                        .setDate(dailyTaskDTO.getSetDate())
                        .tags(dailyTaskDTO
                                .getTags()
                                .stream()
                                .map(id -> tagRepository.findById(id).orElseThrow(() -> new NoSuchTagException(id)))
                                .toList())
                        .isFinished(dailyTaskDTO.isFinished())
                        .build();
            }
        };

        modelMapper.addConverter(dailyTaskConverter);

        Converter<ScheduledTask, ScheduledTaskDTO> scheduledTaskDTOConverter = new Converter<ScheduledTask, ScheduledTaskDTO>() {
            @Override
            public ScheduledTaskDTO convert(MappingContext<ScheduledTask, ScheduledTaskDTO> context) {
                ScheduledTask scheduledTask = context.getSource();
                return ScheduledTaskDTO.builder()
                        .id(scheduledTask.getId())
                        .iconId(scheduledTask.getIconId())
                        .name(scheduledTask.getName())
                        .tags(scheduledTask.getTags().stream().map(Tag::getId).toList())
                        .build();
            }
        };

        modelMapper.addConverter(scheduledTaskDTOConverter);

        Converter<ScheduledTaskDTO, ScheduledTask> scheduledTaskConverter = new Converter<ScheduledTaskDTO, ScheduledTask>() {
            @Override
            public ScheduledTask convert(MappingContext<ScheduledTaskDTO, ScheduledTask> context) {
                ScheduledTaskDTO scheduledTaskDTO = context.getSource();
                return ScheduledTask.builder()
                        .id(scheduledTaskDTO.getId())
                        .iconId(scheduledTaskDTO.getIconId())
                        .name(scheduledTaskDTO.getName())
                        .tags(scheduledTaskDTO
                                .getTags()
                                .stream()
                                .map(id -> tagRepository.findById(id).orElseThrow(() -> new NoSuchTagException(id)))
                                .toList())
                        .build();
            }
        };

        modelMapper.addConverter(scheduledTaskConverter);

        Converter<SampleScheduledTaskDTO, ScheduledTask> sampleScheduledTaskConverter = new Converter<SampleScheduledTaskDTO, ScheduledTask>() {
            @Override
            public ScheduledTask convert(MappingContext<SampleScheduledTaskDTO, ScheduledTask> context) {
                SampleScheduledTaskDTO sampleScheduledTaskDTO = context.getSource();
                return ScheduledTask.builder()
                        .id(sampleScheduledTaskDTO.getId())
                        .iconId(sampleScheduledTaskDTO.getIconId())
                        .name(sampleScheduledTaskDTO.getName())
                        .tags(sampleScheduledTaskDTO
                                .getTags()
                                .stream()
                                .map(id -> tagRepository.findById(id).orElseThrow(() -> new NoSuchTagException(id)))
                                .toList())
                        .categories(sampleScheduledTaskDTO
                                .getCategories()
                                .stream()
                                .map(id -> categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id)))
                                .toList())
                        .build();
            }
        };

        modelMapper.addConverter(sampleScheduledTaskConverter);

        Converter<ScheduledTask, SampleScheduledTaskDTO> sampleScheduledTaskDTOConverter = new Converter<ScheduledTask, SampleScheduledTaskDTO>() {
            @Override
            public SampleScheduledTaskDTO convert(MappingContext<ScheduledTask, SampleScheduledTaskDTO> context) {
                ScheduledTask scheduledTask = context.getSource();
                return SampleScheduledTaskDTO.builder()
                        .id(scheduledTask.getId())
                        .iconId(scheduledTask.getIconId())
                        .name(scheduledTask.getName())
                        .tags(scheduledTask.getTags().stream().map(Tag::getId).toList())
                        .categories(scheduledTask
                                .getCategories()
                                .stream()
                                .map(Category::getId)
                                .toList())
                        .build();
            }
        };

        modelMapper.addConverter(sampleScheduledTaskDTOConverter);

        Converter<Tag, TagDTO> tagDTOConverter = new Converter<Tag, TagDTO>() {
            @Override
            public TagDTO convert(MappingContext<Tag, TagDTO> context) {
                Tag tag = context.getSource();
                return TagDTO.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .build();
            }
        };

        modelMapper.addConverter(tagDTOConverter);

        Converter<TagDTO, Tag> tagConverter = new Converter<TagDTO, Tag>() {
            @Override
            public Tag convert(MappingContext<TagDTO, Tag> context) {
                TagDTO tagDTO = context.getSource();
                return Tag.builder()
                        .id(tagDTO.getId())
                        .name(tagDTO.getName())
                        .build();
            }
        };

        modelMapper.addConverter(tagConverter);

        return modelMapper;
    }
}
