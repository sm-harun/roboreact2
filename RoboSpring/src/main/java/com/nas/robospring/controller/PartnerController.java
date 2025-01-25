package com.nas.robospring.controller;

import com.nas.robospring.model.Partner;
import com.nas.robospring.service.PartnerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/partners")
public class PartnerController {
    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @PostMapping
    public Mono<Partner> createPartner(@RequestBody Partner partner) {
        return partnerService.createPartner(partner);
    }

    @GetMapping("/{id}")
    public Mono<Partner> getPartnerById(@PathVariable Long id) {
        return partnerService.getPartnerById(id);
    }

    @GetMapping
    public Flux<Partner> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePartner(@PathVariable Long id) {
        return partnerService.deletePartner(id);
    }
}
