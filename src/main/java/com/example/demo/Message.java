package com.example.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Message {
    @Id
    private String id;

    @NotNull
    private String from;

    @NotNull
    private String message;

    @NotNull
    private Date when;

    public Message(@NotNull String from, @NotNull String message) {
        this.from = from;
        this.message = message;
        this.when = new Date();
    }
}
