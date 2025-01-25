package com.nas.robospring.repository;

import com.nas.robospring.model.Registration;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RegistrationRepository extends ReactiveCrudRepository<Registration, Long> {
    // You may want to create methods to find registrations by competitionId or userId
}
