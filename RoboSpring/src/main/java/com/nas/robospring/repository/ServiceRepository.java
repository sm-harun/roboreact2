package com.nas.robospring.repository;

import com.nas.robospring.model.Service;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ServiceRepository extends ReactiveCrudRepository<Service, Long> {
    // Additional query methods can be defined here if needed
}
