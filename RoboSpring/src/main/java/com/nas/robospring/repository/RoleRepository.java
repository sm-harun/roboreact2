package com.nas.robospring.repository;

import com.nas.robospring.model.Role;
import com.nas.robospring.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {


    Mono<Role> findByName(String name);
    // Custom query to fetch roles based on a specific user_id from a join table
    @Query("SELECT r.* FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = :userId")
    Flux<Role> findRolesByUserId(Long userId);



}
