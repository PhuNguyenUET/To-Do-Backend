package com.skyline.todo.DTO;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleScheduledTaskDTO {
    private int id;

    private int iconId;

    private String name;

    private List<Integer> tags;

    private List<Integer> categories;
}
