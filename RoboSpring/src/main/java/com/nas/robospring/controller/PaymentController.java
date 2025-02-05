package com.nas.robospring.controller;

import com.nas.robospring.model.Payment;
import com.nas.robospring.model.PaymentTransaction;
import com.nas.robospring.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/initialize")
    public Mono<String> initializePayment(@RequestBody PaymentTransaction paymentTransaction) {
        return paymentService.initializePayment(paymentTransaction);
    }

    @GetMapping("/verify/{txRef}")
    public Mono<PaymentTransaction> verifyPayment(@PathVariable String txRef) {
        return paymentService.verifyPayment(txRef);
    }
    @PostMapping
    public Mono<PaymentTransaction> createPayment(@RequestBody PaymentTransaction payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/user/{userId}")
    public Flux<PaymentTransaction> getPaymentsByUserId(@PathVariable Long userId) {
        return paymentService.getPaymentsByUserId(userId);
    }

    @GetMapping
    public Flux<PaymentTransaction> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePayment(@PathVariable Long id) {
        return paymentService.deletePayment(id);
    }
}

