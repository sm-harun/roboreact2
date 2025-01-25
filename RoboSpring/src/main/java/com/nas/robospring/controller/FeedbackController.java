package com.nas.robospring.controller;

import com.nas.robospring.model.Feedback;
import com.nas.robospring.service.FeedbackService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public Mono<Feedback> createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @GetMapping("/course/{courseId}")
    public Flux<Feedback> getFeedbackByCourseId(@PathVariable Long courseId) {
        return feedbackService.getFeedbackByCourseId(courseId);
    }

    @GetMapping
    public Flux<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(id);
    }
}
