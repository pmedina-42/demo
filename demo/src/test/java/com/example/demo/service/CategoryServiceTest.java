package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.CategoryDTO;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        Category category = new Category();
        category.setName("Category");

        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));

        List<CategoryDTO> result = categoryService.getAllCategories();
        assertEquals(1, result.size());
        assertEquals("Category", result.get(0).getName());
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setName("Category");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getCategoryById(1L);
        assertEquals("Category", result.get().getName());
    }

    @Test
    public void testCreateNewCategory() {
        Category category = new Category();
        category.setName("Category");

        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.createNewCategory(category);
        assertEquals("Category", result.getName());
    }

    @Test
    public void testDeleteCategoryById() {
        Category category = new Category();
        category.setName("Category");

        categoryService.deleteCategoryById(1L);
    }
}
