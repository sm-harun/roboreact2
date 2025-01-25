package com.nas.robospring.service;

import com.nas.robospring.model.Competition;
import com.nas.robospring.model.Registration;
import com.nas.robospring.repository.CompetitionRepository;
import com.nas.robospring.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final RegistrationRepository registrationRepository;

    public CompetitionService(CompetitionRepository competitionRepository, RegistrationRepository registrationRepository) {
        this.competitionRepository = competitionRepository;
        this.registrationRepository = registrationRepository;
    }
    public Mono<Competition> createCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Mono<Competition> getCompetitionById(Long id) {
        return competitionRepository.findById(id);
    }

    public Flux<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Mono<Void> deleteCompetition(Long id) {
        return competitionRepository.deleteById(id);
    }
    // Registration logic can handle pricing information
    public void displayCompetitionDetails(Competition competition) {
        if (competition.getPrice() == null) {
            System.out.println("Call for price");
        } else {
            System.out.println("Price: $" + competition.getPrice());
        }
    }

    public Mono<Registration> registerForCompetition(Long competitionId, Long userId) {
        Registration registration = new Registration();
        registration.setCompetitionId(competitionId);
        registration.setUserId(userId);
        return registrationRepository.save(registration);
    }

   /* // Registration logic (update in service according to your needs)
    public Mono<Void> registerForCompetition(Long competitionId) {
        // Registration logic can go here (e.g., creating registration records)
        return Mono.empty(); // Placeholder for registration logic
    }*/
}
