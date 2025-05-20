package com.otimtony.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostEvent {

    private String eventType;
    private Post post;
    private LocalDateTime timestamp;

    public PostEvent(String eventType, Post post) {
        this.eventType = eventType;
        this.post = post;
    }
}
