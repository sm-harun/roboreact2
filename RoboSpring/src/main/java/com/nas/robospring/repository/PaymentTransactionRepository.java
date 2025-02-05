package com.nas.robospring.repository;

import com.nas.robospring.model.Payment;
import com.nas.robospring.model.PaymentTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PaymentTransactionRepository extends ReactiveCrudRepository<PaymentTransaction, Long> {
    Mono<PaymentTransaction> findByTxRef(String txRef);
    Flux<PaymentTransaction> findByUserId(Long userId);

}