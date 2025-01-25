package com.nas.robospring.repository;

import com.nas.robospring.model.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveCrudRepository<Event, Long> {
    Flux<Event> findByTitleContainingIgnoreCase(String title); // Search for events by title
    Flux<Event> findAll(); // This will return all events

}
