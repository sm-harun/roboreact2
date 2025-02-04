package com.nas.robospring.repository;

import com.nas.robospring.model.RoboticsTraining;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoboticsTrainingRepository extends ReactiveCrudRepository<RoboticsTraining, Long> {
}
