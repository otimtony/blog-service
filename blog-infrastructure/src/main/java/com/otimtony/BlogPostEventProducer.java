package com.otimtony;

import com.otimtony.model.PostEvent;
import com.otimtony.model.Post;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

public class BlogPostEventProducer {

//    private static final String TOPIC = "blog-post-events";
//
//    private final KafkaTemplate<String, PostEvent> kafkaTemplate;
//
//    public BlogPostEventProducer(KafkaTemplate<String, PostEvent> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendBlogPostEvent(Post post, String eventType) {
//        PostEvent event = new PostEvent();
//        event.setEventType(eventType);
//        event.setPostId(post.getId());
//        event.setTitle(post.getTitle());
//        event.setEventTime(LocalDateTime.now());
//
//        kafkaTemplate.send(TOPIC, "post-" + post.getId(), event);
//    }
//
//    public void sendPostDeletedEvent(Post post) {
//        PostEvent event = new PostEvent();
//        event.setEventType(PostEvent.DELETED);
//        event.setPostId(post.getId());
//        event.setTitle(post.getTitle());
//        event.setEventTime(LocalDateTime.now());
//
//        // You might add additional metadata for deleted posts
//        kafkaTemplate.send(TOPIC, "post-deleted-" + post.getId(), event);
//    }
}
