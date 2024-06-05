package com.skyline.todo.model.scheduledTask;

import com.skyline.todo.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ScheduledTask {
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

    @ManyToMany
    @JoinTable(
            name = "scheduled_task_tag",
            joinColumns = @JoinColumn(name = "scheduled_task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.ALL)
    private List<Category> categories;
}
