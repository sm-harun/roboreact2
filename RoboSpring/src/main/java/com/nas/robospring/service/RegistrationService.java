package com.nas.robospring.service;

import com.nas.robospring.model.Registration;
import com.nas.robospring.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Mono<Registration> registerForCompetition(Long competitionId, Long userId) {
        Registration registration = new Registration();
        registration.setCompetitionId(competitionId);
        registration.setUserId(userId);
        return registrationRepository.save(registration);
    }
}
