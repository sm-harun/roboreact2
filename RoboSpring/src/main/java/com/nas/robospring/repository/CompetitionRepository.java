package com.nas.robospring.repository;

import com.nas.robospring.model.Competition;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CompetitionRepository extends ReactiveCrudRepository<Competition, Long> {
    Mono<Void> deleteById(Long id); // This will delete an entity by its ID

}
