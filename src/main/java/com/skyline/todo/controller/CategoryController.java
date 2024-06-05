package com.skyline.todo.controller;

import com.skyline.todo.model.scheduledTask.Category;
import com.skyline.todo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @PostMapping("/add")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid Category category) {
        Category cate = categoryService.post(category);

        return ResponseEntity.ok(cate);
    }

    @PutMapping("/update")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category, @RequestParam("id") int cateId) {
        Category cate = categoryService.update(category, cateId);

        return ResponseEntity.ok(cate);
    }

    @DeleteMapping("/delete")
    @Secured("ROLE_ADIN")
    public ResponseEntity<?> deleteCategory(@RequestParam("id") int cateId) {
        categoryService.delete(cateId);

        return ResponseEntity.ok().build();
    }
}
