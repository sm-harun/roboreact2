package com.nas.robospring.repository;

import com.nas.robospring.model.Feedback;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface FeedbackRepository extends ReactiveCrudRepository<Feedback, Long> {
   // Flux<Feedback> findByCourse_Id(Long courseId); // Fetch feedback for a specific course
    Flux<Feedback> findByCourseId(Long courseId);

}
