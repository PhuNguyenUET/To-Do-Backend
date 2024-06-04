package com.skyline.todo.model.dailyTask;

import com.skyline.todo.model.sampleTask.Tag;
import com.skyline.todo.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToMany(mappedBy = "dailyTasks")
    private List<Tag> tags;

    private LocalDate setDate;

    private boolean isFinished;
}
