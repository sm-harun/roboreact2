package com.nas.robospring.controller;


import com.nas.robospring.model.Address;
import com.nas.robospring.service.AddressService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public Flux<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Mono<Address> getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public Mono<Address> createAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @PutMapping("/{id}")
    public Mono<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        address.setId(id);
        return addressService.saveAddress(address);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAddress(@PathVariable Integer id) {
        return addressService.deleteAddressById(id);
    }
}
