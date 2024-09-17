package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.CategoryDTO;
import com.example.demo.model.Message;
import com.example.demo.service.CategoryService;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(value = "/categories")
    public List<Category> getCategories() {
        return this.categoryService.getAllCategories();
    }

    @GetMapping(value = "categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = this.categoryService.getCategoryById(id);
        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.orElse(null), HttpStatus.OK);
    }

    @GetMapping(value = "categories/name/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        CategoryDTO category = this.categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(this.categoryService.createNewCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        this.categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
