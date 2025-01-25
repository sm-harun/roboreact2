package com.nas.robospring.repository;

import com.nas.robospring.model.Quiz;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface QuizRepository extends ReactiveCrudRepository<Quiz, Long> {
    Flux<Quiz> findByCourseId(Long courseId); // Fetch quizzes for a specific course
}
