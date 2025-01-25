package com.nas.robospring.service;

import com.nas.robospring.model.Application;
import com.nas.robospring.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Mono<Application> submitApplication(Application application) {
        return applicationRepository.save(application);
    }
}
