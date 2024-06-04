package com.skyline.todo.service;

import com.skyline.todo.model.sampleTask.Category;
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
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if(categoryOptional.isEmpty()) {
            //TODO: Throw custom exception
        }

        category.setId(id);
        return categoryRepository.save(category);
    }

    public void delete(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if(categoryOptional.isEmpty()) {
            //TODO: Throw custom exception
        }

        categoryRepository.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
