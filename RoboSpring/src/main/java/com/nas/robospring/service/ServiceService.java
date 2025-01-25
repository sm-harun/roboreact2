package com.nas.robospring.service;

import com.nas.robospring.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

   /* public Mono<Service> createService(Service service) {
        return serviceRepository.save(service);
    }*/

    public Mono<com.nas.robospring.model.Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public Flux<com.nas.robospring.model.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Mono<Void> deleteService(Long id) {
        return serviceRepository.deleteById(id);
    }
}

/*

import com.nas.robospring.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public Flux<Service> getAllServices() {
        return serviceRepository.findAll(); // Fetch all services
    }

    public Mono<Service> createService(Service service) {
        return serviceRepository.save(service); // Create a new service
    }

    public Mono<Service> updateService(Long id, Service serviceDetails) {
        return serviceRepository.findById(id)
                .flatMap(service -> {
                    service.setName(serviceDetails.getName());
                    service.setDescription(serviceDetails.getDescription());
                    return serviceRepository.save(service);
                });
    }

    public Mono<Void> deleteService(Long id) {
        return serviceRepository.deleteById(id); // Delete service by ID
    }
}
*/
