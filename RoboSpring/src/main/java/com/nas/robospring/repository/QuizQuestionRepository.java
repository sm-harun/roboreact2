package com.nas.robospring.repository;

import com.nas.robospring.model.QuizQuestion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface QuizQuestionRepository extends ReactiveCrudRepository<QuizQuestion, Long> {
    // Methods for managing quiz questions can be added here
}
