package com.nas.robospring.repository;

import com.nas.robospring.model.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TeamRepository extends ReactiveCrudRepository<Team, Long> {
    Flux<Team> findByName(String name); // Example method to find teams by name
}
