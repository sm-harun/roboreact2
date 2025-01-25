package com.nas.robospring.repository;

import com.nas.robospring.model.PaymentMethod;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends ReactiveCrudRepository<PaymentMethod, Integer> {
    // Additional query methods can be defined here if needed
}
