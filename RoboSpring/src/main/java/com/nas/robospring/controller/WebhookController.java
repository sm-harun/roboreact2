package com.nas.robospring.controller;

import com.nas.robospring.repository.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @PostMapping("/chapa")
    public Mono<ResponseEntity<String>> handleWebhook(@RequestBody Map<String, Object> payload) {
        String txRef = (String) payload.get("tx_ref");
        String status = (String) payload.get("status");

        return paymentTransactionRepository.findByTxRef(txRef)
                .flatMap(transaction -> {
                    transaction.setStatus(status);
                    return paymentTransactionRepository.save(transaction);
                })
                .thenReturn(ResponseEntity.ok("Webhook received"))
                .onErrorReturn(ResponseEntity.internalServerError().body("Webhook processing failed"));
    }
}
/*
Webhook Setup
Webhooks allow Chapa to send real-time notifications to your server when a payment is completed. You need to:
Create a Webhook Endpoint in your Spring Boot application.
Register the Webhook URL in the Chapa Dashboard.
Log in to your Chapa Dashboard.

Go to Settings > Webhooks.
Add your webhook URL (e.g., https://your-backend-url/webhook/chapa).
Save the changes.

Webhook: Set up a webhook endpoint in your Spring Boot application to handle payment notifications from Chapa.
Frontend: Use React or React Native to initialize payments and redirect users to the Chapa payment page.
Payment Verification: Verify the payment status using the tx_ref after the user completes the payment.

 */