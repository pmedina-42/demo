package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Message;
import com.example.demo.model.MessageDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageServiceTest {
    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMessages() {
        Message message = new Message();
        message.setAuthor("Author");
        message.setContent("Content");
        Category category = new Category();
        category.setName("MOCK");
        message.setCategory(category);

        when(messageRepository.findAll()).thenReturn(Collections.singletonList(message));

        List<MessageDTO> result = messageService.getAllMessages();
        assertEquals(1, result.size());
        assertEquals("Author", result.get(0).getAuthor());
    }

    @Test
    public void testGetMessageById() {
        Message message = new Message();
        message.setAuthor("Author");
        message.setContent("Content");
        Category category = new Category();
        category.setName("MOCK");
        message.setCategory(category);

        when(messageRepository.findById(1L)).thenReturn(Optional.of(message));

        MessageDTO result = messageService.getMessageById(1L);
        assertEquals("Author", result.getAuthor());
    }

    @Test
    public void testDeleteMessageById() {
        Message message = new Message();
        message.setAuthor("Author");
        message.setContent("Content");
        Category category = new Category();
        category.setName("MOCK");
        message.setCategory(category);

        messageService.deleteMessageById(1L);
        verify(messageRepository, times(1)).deleteById(1L);
    }
}
