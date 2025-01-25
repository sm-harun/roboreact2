package com.nas.robospring.repository;

import com.nas.robospring.model.Resource;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ResourceRepository extends ReactiveCrudRepository<Resource, Long> {
    Flux<Resource> findByTitleContainingIgnoreCase(String title); // Search for resources by title
}
