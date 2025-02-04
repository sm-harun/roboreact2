package com.nas.robospring.controller;

import com.nas.robospring.model.Team;
import com.nas.robospring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Mono<Team> createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Team>> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTeam(@PathVariable Long id) {
        return teamService.deleteTeam(id);
    }
}
