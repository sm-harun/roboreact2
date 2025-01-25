package com.nas.robospring.repository;

import com.nas.robospring.model.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Integer> {
    // Additional query methods can be defined here if needed
}
