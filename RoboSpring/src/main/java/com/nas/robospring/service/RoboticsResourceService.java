package com.nas.robospring.service;

import com.nas.robospring.model.RoboticsResource;
import com.nas.robospring.repository.RoboticsResourceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoboticsResourceService {
    private final RoboticsResourceRepository resourceRepository;

    public RoboticsResourceService(RoboticsResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Flux<RoboticsResource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Mono<RoboticsResource> createResource(RoboticsResource resource) {
        return resourceRepository.save(resource);
    }

    public Flux<RoboticsResource> getResourcesByCategory(String categoryId) {
        return resourceRepository.findByCategory(categoryId);
    }
}
