package com.nas.robospring.service;

import com.nas.robospring.model.Team;
import com.nas.robospring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

       public Mono<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public Flux<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Mono<Void> deleteTeam(Long id) {
        return teamRepository.deleteById(id);
    }

    public Mono<Team> createTeam(Team team) {
        return teamRepository.save(team);
    }
}