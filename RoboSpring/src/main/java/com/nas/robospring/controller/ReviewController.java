package com.nas.robospring.controller;
import com.nas.robospring.model.Review;
import com.nas.robospring.service.ReviewService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public Flux<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public Mono<Review> getReviewById(@PathVariable Integer id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public Mono<Review> createReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    @PutMapping("/{id}")
    public Mono<Review> updateReview(@PathVariable Integer id, @RequestBody Review review) {
        review.setId(id);
        return reviewService.saveReview(review);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteReview(@PathVariable Integer id) {
        return reviewService.deleteReviewById(id);
    }
}
