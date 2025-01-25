package com.nas.robospring.service;

import com.nas.robospring.model.Payment;
import com.nas.robospring.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Mono<Payment> createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Flux<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Flux<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Mono<Void> deletePayment(Long id) {
        return paymentRepository.deleteById(id);
    }
}

/*

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Mono<Payment> createPayment(Payment payment) {
        payment.setCreatedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Flux<Payment> getAllPayments() {
        return paymentRepository.findAll(); // List all payments
    }
}
*/
