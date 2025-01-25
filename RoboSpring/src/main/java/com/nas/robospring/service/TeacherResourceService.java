package com.nas.robospring.service;


import com.nas.robospring.model.TeacherResource;
import com.nas.robospring.repository.TeacherResourceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TeacherResourceService {
    private final TeacherResourceRepository resourceRepository;

    public TeacherResourceService(TeacherResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Mono<TeacherResource> createResource(TeacherResource resource) {
        return resourceRepository.save(resource);
    }

    public Flux<TeacherResource> getAllResources() {
        return resourceRepository.findAll();
    }
}
