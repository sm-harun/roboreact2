package com.nas.robospring.controller;
import com.nas.robospring.model.RoboticsTraining;
import com.nas.robospring.service.RoboticsTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/robotics-trainings")
public class RoboticsTrainingController {

    @Autowired
    private RoboticsTrainingService trainingService;

    @GetMapping
    public Flux<RoboticsTraining> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{id}")
    public Mono<RoboticsTraining> getTrainingById(@PathVariable Long id) {
        return trainingService.getTrainingById(id);
    }

    @PostMapping
    public Mono<RoboticsTraining> createTraining(@RequestBody RoboticsTraining training) {
        return trainingService.createTraining(training);
    }

    @PostMapping("/batch")
    public Flux<RoboticsTraining> createTrainings(@RequestBody Flux<RoboticsTraining> trainings) {
        return trainingService.createTrainings(trainings);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTraining(@PathVariable Long id) {
        return trainingService.deleteTraining(id);
    }
}
