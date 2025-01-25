package com.nas.robospring.service;

import com.nas.robospring.model.CommunityService;
import com.nas.robospring.repository.CommunityServiceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommunityServiceService {
    private final CommunityServiceRepository communityServiceRepository;

    public CommunityServiceService(CommunityServiceRepository communityServiceRepository) {
        this.communityServiceRepository = communityServiceRepository;
    }

    public Mono<CommunityService> createCommunityService(CommunityService communityService) {
        return communityServiceRepository.save(communityService);
    }

    public Mono<CommunityService> getCommunityServiceById(Long id) {
        return communityServiceRepository.findById(id);
    }

    public Flux<CommunityService> getAllCommunityServices() {
        return communityServiceRepository.findAll();
    }

    public Mono<Void> deleteCommunityService(Long id) {
        return communityServiceRepository.deleteById(id);
    }
}
