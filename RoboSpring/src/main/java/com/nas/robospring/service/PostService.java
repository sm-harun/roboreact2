package com.nas.robospring.service;

import com.nas.robospring.model.Post;
import com.nas.robospring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Mono<Post> createPost(Post post) {
        post.setPostedOn(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Mono<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
    public Flux<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
    public Flux<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Mono<Void> deletePost(Long id) {
        return postRepository.deleteById(id);
    }
}