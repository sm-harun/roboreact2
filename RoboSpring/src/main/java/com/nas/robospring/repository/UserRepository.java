package com.nas.robospring.repository;

import com.nas.robospring.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByEmail(String email); // Method to find user by email
    Mono<User> findByUsername(String username);

}

/*

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
}

//public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
//    Mono<User> findByUsername(String username);
Mono<User> findByUsername(String username);

Mono<User> findByEmail(String email); // Method to find user by email

//}*/
