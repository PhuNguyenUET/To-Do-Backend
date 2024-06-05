package com.skyline.todo.repository;

import com.skyline.todo.model.scheduledTask.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findByUserEmail(String userEmail, Pageable pageable);

    default List<Tag> findFirst3ByUserEmail(String userEmail) {
        return findByUserEmail(userEmail, PageRequest.of(0, 3, Sort.by(Sort.Order.asc("id"))));
    }

    default List<Tag> findAllByUserEmail(String userEmail) {
        return findByUserEmail(userEmail, Pageable.unpaged());
    }
}
