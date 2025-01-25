package com.nas.robospring.repository;

import com.nas.robospring.model.Competition;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CompetitionRepository extends ReactiveCrudRepository<Competition, Long> {}
