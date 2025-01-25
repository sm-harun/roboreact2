package com.nas.robospring.repository;

import com.nas.robospring.model.RoboticsResource;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RoboticsResourceRepository extends ReactiveCrudRepository<RoboticsResource, Long> {
    Flux<RoboticsResource> findByCategory(String categoryId);
    // Additional query methods can be defined here if needed
}
