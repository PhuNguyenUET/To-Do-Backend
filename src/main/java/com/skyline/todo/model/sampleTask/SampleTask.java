package com.skyline.todo.model.sampleTask;

import com.skyline.todo.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SampleTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer iconId;

    private String name;

    @Enumerated(EnumType.STRING)
    private RepeatMode repeatMode;

    private List<String> repeatTime;

    @ManyToOne
    @CreatedBy
    @JoinColumn(
            name = "created_by",
            nullable = false,
            updatable = false
    )
    private User user;

    @ManyToMany(mappedBy = "sampleTasks")
    private List<Tag> tags;

    @ManyToMany(mappedBy = "tasks")
    private List<Category> categories;
}
