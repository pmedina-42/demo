package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Message;
import com.example.demo.model.MessageDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MessageService {

    private final MessageRepository repository;
    private final CategoryRepository categoryRepository;

    public List<MessageDTO> getAllMessages() {
        List<Message> entities = repository.findAll();
        List<MessageDTO> dtoList = new ArrayList<>();
        for (Message entity : entities) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setContent(entity.getContent());
            messageDTO.setAuthor(entity.getAuthor());
            messageDTO.setCategory(entity.getCategory().getName());
            dtoList.add(messageDTO);
        }
        return dtoList;
    }

    public MessageDTO getMessageById(Long id) {
        MessageDTO messageDTO = new MessageDTO();
        Optional<Message> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        messageDTO.setAuthor(entity.get().getAuthor());
        messageDTO.setContent(entity.get().getContent());
        messageDTO.setCategory(entity.get().getCategory().getName());
        return messageDTO;
    }

    public MessageDTO createNewMessage(MessageDTO message) {
        Optional<Category> category = this.categoryRepository.findByName(message.getCategory());
        if (null == message.getCategory() || message.getCategory().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Message newMessage = new Message();
        newMessage.setAuthor(null == message.getAuthor() ? "Jane Doe" : message.getAuthor());
        newMessage.setContent(message.getContent());

        if (category.isEmpty()) {
            Category c = new Category();
            c.setName(message.getCategory() );
            c.setMessages(Collections.singletonList(newMessage));
            this.categoryRepository.save(c);
            newMessage.setCategory(c);
        } else {
            newMessage.setCategory(category.get());
        }
        newMessage = this.repository.save(newMessage);
        message.setAuthor(newMessage.getAuthor());
        return message;
    }

    public void deleteMessageById(Long id) {
        this.repository.deleteById(id);
    }
}
