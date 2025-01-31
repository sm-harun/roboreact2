package com.nas.robospring.repository;

import com.nas.robospring.model.VexRobotics;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface VexRoboticsRepository extends ReactiveCrudRepository<VexRobotics, Long> {
    Flux<VexRobotics> findByTargetAudience(String targetAudience);
}