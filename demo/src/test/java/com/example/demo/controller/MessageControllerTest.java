package com.example.demo.controller;

import com.example.demo.model.MessageDTO;
import com.example.demo.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageControllerTest {
    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMessages() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setAuthor("Author");
        messageDTO.setContent("Content");
        messageDTO.setCategory("Category");

        when(messageService.getAllMessages()).thenReturn(Collections.singletonList(messageDTO));

        List<MessageDTO> result = messageController.getMessages();
        assertEquals(1, result.size());
        assertEquals("Author", result.get(0).getAuthor());
    }

    @Test
    public void testGetMessageById() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setAuthor("Author");
        messageDTO.setContent("Content");
        messageDTO.setCategory("Category");

        when(messageService.getMessageById(1L)).thenReturn(messageDTO);

        ResponseEntity<MessageDTO> response = messageController.getMessageById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Author", response.getBody().getAuthor());
    }
}
