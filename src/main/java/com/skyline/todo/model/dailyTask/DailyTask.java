package com.skyline.todo.model.dailyTask;

import com.skyline.todo.model.scheduledTask.Tag;
import com.skyline.todo.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class DailyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDate createdDate;

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
            name = "daily_task_tag",
            joinColumns = @JoinColumn(name = "daily_task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    private LocalDate setDate;

    @Value("false")
    private boolean isFinished;
}
