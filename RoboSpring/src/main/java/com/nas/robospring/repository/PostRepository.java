package com.nas.robospring.repository;

import com.nas.robospring.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveCrudRepository<Post, Long> {
    Flux<Post> findByTitleContaining(String title);
    // Example search function
    Flux<Post> findByUserId(Long userId); // Find posts by user ID

}
