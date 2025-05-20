package com.otimtony;

import com.otimtony.model.PostEvent;
import com.otimtony.model.Post;
import com.otimtony.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final PostRepository repository;
    private final KafkaTemplate<String, PostEvent> kafkaTemplate;

    @Autowired
    public BlogService(PostRepository repository, KafkaTemplate<String, PostEvent> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = repository.save(post);

        // Send Kafka event
        PostEvent event = new PostEvent("POST_CREATED", savedPost);
        kafkaTemplate.send("blog-events", event);

        return savedPost;
    }

    public Optional<Post> getPost(Long id) {
        return repository.findById(id);
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Post> updatePost(Long id, Post updatedPost) {
        return repository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setUpdatedAt(LocalDateTime.now());
            Post savedPost = repository.save(post);

            // Send Kafka event
            PostEvent event = new PostEvent("POST_UPDATED", savedPost);
            kafkaTemplate.send("blog-events", event);

            return savedPost;
        });
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        repository.deleteById(id);

        // Send Kafka event
        PostEvent event = new PostEvent("POST_DELETED", post);
        kafkaTemplate.send("blog-events", event);
    }

}
