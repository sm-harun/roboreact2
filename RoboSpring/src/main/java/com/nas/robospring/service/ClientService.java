package com.nas.robospring.service;

import com.nas.robospring.model.Client;
import com.nas.robospring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Mono<Client> createClient(Client client) {
        client.setCreatedAt(LocalDateTime.now()); // Set created timestamp
        return clientRepository.save(client);
    }

    public Mono<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Flux<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Mono<Void> deleteClient(Long id) {
        return clientRepository.deleteById(id);
    }
}
