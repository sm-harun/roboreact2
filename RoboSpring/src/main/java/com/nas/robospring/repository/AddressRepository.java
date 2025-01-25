package com.nas.robospring.repository;

import com.nas.robospring.model.Address;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Integer> {
    // Additional query methods can be defined here if needed
}
