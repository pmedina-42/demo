package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.model.MessageDTO;
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
public class MessageController {

    private final MessageService messageService;

    @GetMapping(value = "/messages")
    public List<MessageDTO> getMessages() {
        return this.messageService.getAllMessages();
    }

    @GetMapping(value = "messages/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
        MessageDTO message = this.messageService.getMessageById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(value = "/messages")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO message) {
        return new ResponseEntity<>(this.messageService.createNewMessage(message), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/messages/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        this.messageService.deleteMessageById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
