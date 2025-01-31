package com.nas.robospring.service;

import com.nas.robospring.model.VexRobotics;
import com.nas.robospring.repository.VexRoboticsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VexRoboticsService {
    private final VexRoboticsRepository repository;

    public VexRoboticsService(VexRoboticsRepository repository) {
        this.repository = repository;
    }

    public Flux<VexRobotics> findAll() {
        return repository.findAll();
    }

    public Mono<VexRobotics> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<VexRobotics> save(VexRobotics vexRobotics) {
        return repository.save(vexRobotics);
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}