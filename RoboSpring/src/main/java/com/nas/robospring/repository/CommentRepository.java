package com.nas.robospring.repository;

import com.nas.robospring.model.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CommentRepository extends ReactiveCrudRepository<Comment, Long> {
    Flux<Comment> findByPostId(Long postId); // Fetch comments by post ID
}
