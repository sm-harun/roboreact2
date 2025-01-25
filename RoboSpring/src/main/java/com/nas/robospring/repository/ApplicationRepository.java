package com.nas.robospring.repository;

import com.nas.robospring.model.Application;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ApplicationRepository extends ReactiveCrudRepository<Application, Long> {}
