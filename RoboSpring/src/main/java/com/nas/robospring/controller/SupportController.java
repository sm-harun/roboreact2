package com.nas.robospring.controller;

import com.nas.robospring.model.Support;
import com.nas.robospring.service.SupportService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/support")
public class SupportController {
    private final SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

    @PostMapping
    public Mono<Support> createSupport(@RequestBody Support support) {
        return supportService.createSupport(support);
    }

    @GetMapping("/{id}")
    public Mono<Support> getSupportById(@PathVariable Long id) {
        return supportService.getSupportById(id);
    }

    @GetMapping
    public Flux<Support> getAllSupport() {
        return supportService.getAllSupport();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteSupport(@PathVariable Long id) {
        return supportService.deleteSupport(id);
    }
}
