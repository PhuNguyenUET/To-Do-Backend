package com.skyline.todo.repository;

import com.skyline.todo.model.sampleTask.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
