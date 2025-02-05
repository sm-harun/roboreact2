package com.nas.robospring.controller;

import com.nas.robospring.model.Post;
import com.nas.robospring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Mono<Post> createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Post>> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping("/user/{userId}")
    public Flux<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }
    @GetMapping
    public Flux<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
