package com.nas.robospring.controller;

import com.nas.robospring.model.Service;
import com.nas.robospring.service.ServiceService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

 /*   @PostMapping
    public Mono<Void> createService(@RequestBody Service service) {
        return serviceService.createService((org.springframework.stereotype.Service) service);
    }*/

    @GetMapping("/{id}")
    public Mono<Service> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @GetMapping
    public Flux<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteService(@PathVariable Long id) {
        return serviceService.deleteService(id);
    }
}
