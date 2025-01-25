package com.nas.robospring.controller;

import com.nas.robospring.model.Competition;
import com.nas.robospring.model.Registration;
import com.nas.robospring.service.CompetitionService;
import com.nas.robospring.service.RegistrationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;
    private final RegistrationService registrationService;

    public CompetitionController(CompetitionService competitionService, RegistrationService registrationService) {
        this.competitionService = competitionService;
        this.registrationService = registrationService;
    }

    @PostMapping
    public Mono<Competition> createCompetition(@RequestBody Competition competition) {
        return competitionService.createCompetition(competition);
    }

    @GetMapping("/{id}")
    public Mono<Competition> getCompetitionById(@PathVariable Long id) {
        return competitionService.getCompetitionById(id);
    }

    @GetMapping
    public Flux<Competition> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCompetition(@PathVariable Long id) {
        return competitionService.deleteCompetition(id);
    }

    @PostMapping("/{id}/register")
    public Mono<Registration> registerForCompetition(@PathVariable Long id, @RequestBody Long userId) {
        // Assume userId is passed in the request body
        return registrationService.registerForCompetition(id, userId);
    }
    /*@PostMapping("/{id}/register")
    public Mono<Void> registerForCompetition(@PathVariable Long id) {
        return competitionService.registerForCompetition(id);
    }*/
}
