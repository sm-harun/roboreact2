package com.nas.robospring.repository;

import com.nas.robospring.model.TimelineEvent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TimelineEventRepository extends ReactiveCrudRepository<TimelineEvent, Long> {
    Flux<TimelineEvent> findAll(); // Fetch all timeline events
}
