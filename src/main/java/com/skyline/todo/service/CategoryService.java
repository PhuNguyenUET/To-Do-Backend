package com.skyline.todo.service;

import com.skyline.todo.model.scheduledTask.Category;
import com.skyline.todo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category post(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category, int id) {
        categoryRepository.findById(id).orElseThrow();

        category.setId(id);
        return categoryRepository.save(category);
    }

    public void delete(int id) {
        categoryRepository.findById(id).orElseThrow();

        categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
