package com.nas.robospring.controller;

import com.nas.robospring.model.Client;
import com.nas.robospring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Mono<Client> createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Client>> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok) // Wrap the client in a ResponseEntity
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Handle 404 case
    }

    @GetMapping
    public Flux<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }
}
