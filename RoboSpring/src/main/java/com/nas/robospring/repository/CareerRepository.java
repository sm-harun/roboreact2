package com.nas.robospring.repository;

import com.nas.robospring.model.Career;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CareerRepository extends ReactiveCrudRepository<Career, Long> {}
