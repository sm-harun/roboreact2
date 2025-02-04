package com.nas.robospring.repository;

import com.nas.robospring.model.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
    Flux<Client> findByName(String name); // Example method to find clients by name
}
