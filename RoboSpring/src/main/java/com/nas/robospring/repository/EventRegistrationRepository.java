package com.nas.robospring.repository;

import com.nas.robospring.model.EventRegistration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventRegistrationRepository extends ReactiveCrudRepository<EventRegistration, Long> {
    Flux<EventRegistration> findByUserId(Long userId); // Custom query method to find registrations by userId
}
