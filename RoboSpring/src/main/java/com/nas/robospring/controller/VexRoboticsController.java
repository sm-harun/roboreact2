package com.nas.robospring.controller;

import com.nas.robospring.model.VexRobotics;
import com.nas.robospring.service.VexRoboticsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vex-robotics")
public class VexRoboticsController {
    private final VexRoboticsService service;

    public VexRoboticsController(VexRoboticsService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<VexRobotics> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<VexRobotics> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VexRobotics> create(@RequestBody VexRobotics vexRobotics) {
        return service.save(vexRobotics);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
