package com.nas.robospring.controller;

import com.nas.robospring.model.Career;
import com.nas.robospring.service.CareerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/careers")
public class CareerController {
    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping
    public Mono<Career> createCareer(@RequestBody Career career) {
        return careerService.createCareer(career);
    }

    @GetMapping("/{id}")
    public Mono<Career> getCareerById(@PathVariable Long id) {
        return careerService.getCareerById(id);
    }

    @GetMapping
    public Flux<Career> getAllCareers() {
        return careerService.getAllCareers();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCareer(@PathVariable Long id) {
        return careerService.deleteCareer(id);
    }
}
