package com.nas.robospring.service;

import com.nas.robospring.model.CommunityPost;
import com.nas.robospring.repository.CommunityPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommunityPostService {
    private final CommunityPostRepository communityPostRepository;

    public CommunityPostService(CommunityPostRepository communityPostRepository) {
        this.communityPostRepository = communityPostRepository;
    }

    public Mono<CommunityPost> createPost(CommunityPost post) {
        return communityPostRepository.save(post);
    }

    public Flux<CommunityPost> getAllPosts() {
        return communityPostRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Flux<CommunityPost> searchPosts(String query) {
        return communityPostRepository.findByTitleContainingIgnoreCase(query);
    }
}
