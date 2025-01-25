package com.nas.robospring.service;

import com.nas.robospring.model.Review;
import com.nas.robospring.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Flux<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Mono<Review> getReviewById(Integer id) {
        return reviewRepository.findById(id);
    }

    public Mono<Review> saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Mono<Void> deleteReviewById(Integer id) {
        return reviewRepository.deleteById(id);
    }
}
