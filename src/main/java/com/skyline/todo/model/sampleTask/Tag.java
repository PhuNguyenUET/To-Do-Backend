package com.skyline.todo.model.sampleTask;

import com.skyline.todo.model.dailyTask.DailyTask;
import com.skyline.todo.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    @CreatedBy
    @JoinColumn(
            name = "created_by",
            nullable = false,
            updatable = false
    )
    private User user;

    @ManyToMany
    @JoinTable(
            name = "sample_task_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "sample_task_id")
    )
    List<SampleTask> sampleTasks;

    @ManyToMany
    @JoinTable(
            name = "daily_task_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "daily_task_id")
    )
    List<DailyTask> dailyTasks;
}
