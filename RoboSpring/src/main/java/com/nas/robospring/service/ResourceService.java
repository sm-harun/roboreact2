package com.nas.robospring.service;

import com.nas.robospring.model.Resource;
import com.nas.robospring.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Mono<Resource> createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Mono<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    public Flux<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Mono<Void> deleteResource(Long id) {
        return resourceRepository.deleteById(id);
    }
}
/*

import com.nas.robospring.model.Resource;
import com.nas.robospring.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;

    public Flux<Resource> getAllResources() {
        return resourceRepository.findAll(); // Fetch all resources
    }

    public Mono<Resource> createResource(Resource resource) {
        return resourceRepository.save(resource); // Create a new resource
    }

    public Mono<Resource> updateResource(Long id, Resource resourceDetails) {
        return resourceRepository.findById(id)
                .flatMap(resource -> {
                    resource.setTitle(resourceDetails.getTitle());
                    resource.setDescription(resourceDetails.getDescription());
                    resource.setLink(resourceDetails.getLink());
                    return resourceRepository.save(resource);
                });
    }

    public Mono<Void> deleteResource(Long id) {
        return resourceRepository.deleteById(id); // Delete resource by ID
    }
}
*/
