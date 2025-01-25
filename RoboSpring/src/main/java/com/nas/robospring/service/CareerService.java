package com.nas.robospring.service;

import com.nas.robospring.model.Career;
import com.nas.robospring.repository.CareerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CareerService {
    private final CareerRepository careerRepository;

    public CareerService(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }

    public Mono<Career> createCareer(Career career) {
        return careerRepository.save(career);
    }

    public Mono<Career> getCareerById(Long id) {
        return careerRepository.findById(id);
    }

    public Flux<Career> getAllCareers() {
        return careerRepository.findAll();
    }

    public Mono<Void> deleteCareer(Long id) {
        return careerRepository.deleteById(id);
    }
}
