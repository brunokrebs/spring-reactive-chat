package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class DemoApplication {

    @Bean
    CommandLineRunner initData(MessageRepository messageRepository) {
        return args -> {
            messageRepository.deleteAll().thenMany(
                    Flux.just(
                            new Message("Bruno", "Hello"),
                            new Message("Maria", "Howdy!"),
                            new Message("Bruno", "How are you?"),
                            new Message("Maria", "Fine, and you?"),
                            new Message("Bruno", "Great, thanks!")
                    ).flatMap(messageRepository::save)
            ).subscribe(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
