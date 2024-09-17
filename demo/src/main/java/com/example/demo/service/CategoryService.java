package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.CategoryDTO;
import com.example.demo.model.Message;
import com.example.demo.model.MessageDTO;
import com.example.demo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = repository.findAll();
        List<CategoryDTO> response = new ArrayList<>();
        for (Category category : categories) {
            CategoryDTO dto = new CategoryDTO();
            dto.setName(category.getName());
            response.add(dto);
        }
        return response;
    }

    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public CategoryDTO getCategoryByName(String name) {

        Optional<Category> entity = repository.findByName(name);
        if (entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        CategoryDTO category = new CategoryDTO();
        category.setName(entity.get().getName());
        List<MessageDTO> messages = new ArrayList<>();
        for (Message message : entity.get().getMessages()) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setAuthor(message.getAuthor());
            messageDTO.setContent(message.getContent());
            messages.add(messageDTO);
        }
        category.setMessages(messages);
        return category;
    }

    public Category createNewCategory(Category category) {
        if (this.repository.findByName(category.getName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.repository.save(category);
    }

    public void deleteCategoryById(Long id) {
        this.repository.deleteById(id);
    }
}
