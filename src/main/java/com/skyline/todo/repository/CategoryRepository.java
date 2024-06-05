package com.skyline.todo.repository;

import com.skyline.todo.model.scheduledTask.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
