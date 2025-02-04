package com.nas.robospring.service;

import com.nas.robospring.model.RoboticsTraining;
import com.nas.robospring.repository.RoboticsTrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoboticsTrainingService {

    @Autowired
    private RoboticsTrainingRepository repository;

    public Flux<RoboticsTraining> getAllTrainings() {
        return repository.findAll();
    }

    public Mono<RoboticsTraining> getTrainingById(Long id) {
        return repository.findById(id);
    }

    public Mono<RoboticsTraining> createTraining(RoboticsTraining training) {
        return repository.save(training);
    }

    public Mono<Void> deleteTraining(Long id) {
        return repository.deleteById(id);
    }

    public Flux<RoboticsTraining> createTrainings(Flux<RoboticsTraining> trainings) {
        return repository.saveAll(trainings);
    }
}
