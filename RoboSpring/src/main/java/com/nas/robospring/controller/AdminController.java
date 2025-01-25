package com.nas.robospring.controller;

import com.nas.robospring.model.Payment;
import com.nas.robospring.model.User;
import com.nas.robospring.service.PaymentService;
import com.nas.robospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers(); // Return flux of all users
    }

    @GetMapping("/payments")
    public Flux<Payment> getAllPayments() {
        return paymentService.getAllPayments(); // Return flux of all payments
    }

    // Additional methods for monitoring courses, events, etc.
}
