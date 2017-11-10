package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    Flux<Message> getMessages() {
        return messageRepository.findAll();
    }

    @PostMapping
    Mono<Void> addMessage(@RequestBody String message) {
        return messageRepository.insert(new Message("Bruno", message)).then();
    }

    @GetMapping(value = "/infinity", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Message> getInfinityMessages() {
        return messageRepository.findAll();
    }
}
