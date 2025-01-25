package com.nas.robospring.repository;

import com.nas.robospring.model.Course;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {
    Flux<Course> findByTitleContainingIgnoreCase(String title);

    Flux<Course> findByLevel(String level);

    Flux<Course> findByUserId(Long userId);
}
