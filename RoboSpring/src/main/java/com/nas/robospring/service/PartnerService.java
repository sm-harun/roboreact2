package com.nas.robospring.service;

import com.nas.robospring.model.Partner;
import com.nas.robospring.repository.PartnerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public Mono<Partner> createPartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    public Mono<Partner> getPartnerById(Long id) {
        return partnerRepository.findById(id);
    }

    public Flux<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }

    public Mono<Void> deletePartner(Long id) {
        return partnerRepository.deleteById(id);
    }
}
