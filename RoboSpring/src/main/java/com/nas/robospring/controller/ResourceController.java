package com.nas.robospring.controller;

import com.nas.robospring.model.Resource;
import com.nas.robospring.service.ResourceService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public Mono<Resource> createResource(@RequestBody Resource resource) {
        return resourceService.createResource(resource);
    }

    @GetMapping("/{id}")
    public Mono<Resource> getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id);
    }

    @GetMapping
    public Flux<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteResource(@PathVariable Long id) {
        return resourceService.deleteResource(id);
    }
}
