package com.nas.robospring.controller;

import com.nas.robospring.model.CommunityService;
import com.nas.robospring.service.CommunityServiceService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/community-services")
public class CommunityServiceController {
    private final CommunityServiceService communityServiceService;

    public CommunityServiceController(CommunityServiceService communityServiceService) {
        this.communityServiceService = communityServiceService;
    }

    @PostMapping
    public Mono<CommunityService> createCommunityService(@RequestBody CommunityService communityService) {
        return communityServiceService.createCommunityService(communityService);
    }

    @GetMapping("/{id}")
    public Mono<CommunityService> getCommunityServiceById(@PathVariable Long id) {
        return communityServiceService.getCommunityServiceById(id);
    }

    @GetMapping
    public Flux<CommunityService> getAllCommunityServices() {
        return communityServiceService.getAllCommunityServices();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCommunityService(@PathVariable Long id) {
        return communityServiceService.deleteCommunityService(id);
    }
}
