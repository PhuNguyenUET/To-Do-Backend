package com.skyline.todo.DTO;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private int id;
    private String name;
}
