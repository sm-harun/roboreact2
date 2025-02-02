package com.nas.robospring.repository;

import com.nas.robospring.model.Role;
import com.nas.robospring.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Set;


public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByEmail(String email); // Method to find user by email
    Mono<User> findByUsername(String username);
    Mono<Boolean> existsByUsername(String username);
    Mono<Boolean> existsByEmail(String email);
    Mono<User> findByUsernameOrEmail(String username, String email);

    @Query("SELECT r FROM Role r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = $1")
    Mono<Set<Role>> findRolesByUserId(Long userId);
}

/*
public Mono<User> findUserWithRoles(String username) {
    return userRepository.findByUsername(username)
        .flatMap(user -> roleRepository.findRolesByUserId(user.getId())
            .collectList()
            .doOnNext(user::setRoles) // Assuming you have setRoles method
        );
}
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
