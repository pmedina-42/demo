package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.CategoryDTO;
import com.example.demo.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategories() {
        CategoryDTO category = new CategoryDTO();
        category.setName("Category");

        when(categoryService.getAllCategories()).thenReturn(Collections.singletonList(category));

        List<CategoryDTO> result = categoryController.getCategories();
        assertEquals(1, result.size());
        assertEquals("Category", result.get(0).getName());
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setName("Category");

        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category", response.getBody().getName());
    }

    @Test
    public void testGetCategoryByName() {
        CategoryDTO category = new CategoryDTO();
        category.setName("Category");

        when(categoryService.getCategoryByName("Category")).thenReturn(category);

        ResponseEntity<CategoryDTO> response = categoryController.getCategoryByName("Category");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category", response.getBody().getName());
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Category");

        when(categoryService.createNewCategory(category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createCategory(category);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Category", response.getBody().getName());
    }

    @Test
    public void testDeleteCategory() {
        categoryController.deleteCategory(1L);
        verify(categoryService, times(1)).deleteCategoryById(1L);
    }
}
