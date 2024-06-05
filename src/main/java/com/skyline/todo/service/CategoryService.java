package com.skyline.todo.service;

import com.skyline.todo.exceptions.category.NoSuchCategoryException;
import com.skyline.todo.model.scheduledTask.Category;
import com.skyline.todo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category post(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category, int id) {
        categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id));

        category.setId(id);
        return categoryRepository.save(category);
    }

    public void delete(int id) {
        categoryRepository.findById(id).orElseThrow(() -> new NoSuchCategoryException(id));

        categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
