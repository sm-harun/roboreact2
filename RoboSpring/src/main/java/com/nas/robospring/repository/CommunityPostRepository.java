package com.nas.robospring.repository;

import com.nas.robospring.model.CommunityPost;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CommunityPostRepository extends ReactiveCrudRepository<CommunityPost, Long> {
    Flux<CommunityPost> findByTitleContainingIgnoreCase(String title);
}
