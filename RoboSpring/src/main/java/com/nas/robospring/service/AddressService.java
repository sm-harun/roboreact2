package com.nas.robospring.service;

import com.nas.robospring.model.Address;
import com.nas.robospring.repository.AddressRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Flux<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Mono<Address> getAddressById(Integer id) {
        return addressRepository.findById(id);
    }

    public Mono<Address> saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public Mono<Void> deleteAddressById(Integer id) {
        return addressRepository.deleteById(id);
    }
}
