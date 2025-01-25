package com.nas.robospring.service;

import com.nas.robospring.model.Support;
import com.nas.robospring.repository.SupportRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SupportService {
    private final SupportRepository supportRepository;

    public SupportService(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    public Mono<Support> createSupport(Support support) {
        return supportRepository.save(support);
    }

    public Mono<Support> getSupportById(Long id) {
        return supportRepository.findById(id);
    }

    public Flux<Support> getAllSupport() {
        return supportRepository.findAll();
    }

    public Mono<Void> deleteSupport(Long id) {
        return supportRepository.deleteById(id);
    }
}
