package com.nas.robospring.service;

import com.nas.robospring.model.Feedback;
import com.nas.robospring.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Mono<Feedback> createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Flux<Feedback> getFeedbackByCourseId(Long courseId) {
        return feedbackRepository.findByCourseId(courseId);
    }

    public Flux<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Mono<Void> deleteFeedback(Long id) {
        return feedbackRepository.deleteById(id);
    }
}
/*

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Flux<Feedback> getFeedbackByCourseId(Long courseId) {
        return feedbackRepository.findByCourse_Id(courseId); // Fetch feedback for a specific course
    }

    public Mono<Feedback> submitFeedback(Feedback feedback) {
        feedback.setCreatedAt(LocalDateTime.now());
        return feedbackRepository.save(feedback); // Save feedback
    }

    public Mono<Void> deleteFeedback(Long id) {
        return feedbackRepository.deleteById(id); // Delete feedback by ID
    }
}
*/
