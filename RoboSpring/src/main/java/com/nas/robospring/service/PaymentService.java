package com.nas.robospring.service;
import com.nas.robospring.model.PaymentTransaction;
import com.nas.robospring.repository.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import com.nas.robospring.model.Payment;
import com.nas.robospring.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    @Value("${payment.api.base-url}")
    private String baseUrl;

    @Value("${payment.api.secret-key}")
    private String chapaSecretKey;
   /* @Value("${chapa.secret-key}")
    private String chapaSecretKey;*/
    @Value("${payment.callback-url}")
    private String callbackUrl;

    @Value("${payment.return-url}")
    private String returnUrl;
  /*  Chapa API Call:
    The webClient sends the paymentData object to the Chapa API.
    The checkout_url is extracted from the API response and returned to the caller.*/
    private final WebClient webClient;
    private final PaymentTransactionRepository paymentRepository;
//    private final WebClient webClient = WebClient.create("https://api.chapa.co/v1");
    public PaymentService(WebClient.Builder webClientBuilder, PaymentTransactionRepository paymentRepository) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.paymentRepository = paymentRepository;
    }
    public Mono<String> initializePayment(PaymentTransaction paymentTransaction) {
        // Set default values for the transaction
        paymentTransaction.setCreatedAt(LocalDateTime.now());
        paymentTransaction.setStatus("pending");
        paymentTransaction.setCurrency("ETB"); // Set default currency
        paymentTransaction.setTxRef("tx-ref-" + System.currentTimeMillis()); // Generate unique tx_ref

        // Save the transaction to the database
        return paymentRepository.save(paymentTransaction)
                .flatMap(savedTransaction -> {
                    // Create the paymentData object for the Chapa API
                    Map<String, Object> paymentData = Map.of(
                            "amount", savedTransaction.getAmount(),
                            "currency", savedTransaction.getCurrency(),
                            "email", savedTransaction.getEmail(),
                            "phone_number", savedTransaction.getPhoneNumber(),
                            "tx_ref", savedTransaction.getTxRef(),
                            "callback_url", callbackUrl,
                            "return_url", returnUrl
                    );

                    // Call the Chapa API to initialize the payment
                    return webClient.post()
                            .uri("/transaction/initialize")
                            .header("Authorization", "Bearer " + chapaSecretKey)
                            .bodyValue(paymentData)
                            .retrieve()
                            .bodyToMono(Map.class)
                            .map(response -> (String) ((Map) response.get("data")).get("checkout_url"));
                });
    }
  /*  public Mono<String> initializePayment(PaymentTransaction paymentTransaction) {
        paymentTransaction.setCreatedAt(LocalDateTime.now());
        paymentTransaction.setStatus("pending");

        return paymentRepository.save(paymentTransaction)
                .flatMap(savedTransaction -> webClient.post()
                        .uri("/transaction/initialize")
                        .header("Authorization", "Bearer " + chapaSecretKey)
                        .bodyValue(Map.of(
                                "amount", savedTransaction.getAmount(),
                                "currency", savedTransaction.getCurrency(),
                                "email", savedTransaction.getEmail(),
                                "phone_number", savedTransaction.getPhoneNumber(),
                                "tx_ref", savedTransaction.getTxRef(),
                                "callback_url", callbackUrl,
                                "return_url", returnUrl
                        ))
                        .retrieve()
                        .bodyToMono(Map.class)
                        .map(response -> (String) ((Map) response.get("data")).get("checkout_url")));
    }*/

    public Mono<PaymentTransaction> verifyPayment(String txRef) {
        return webClient.get()
                .uri("/transaction/verify/" + txRef)
                .header("Authorization", "Bearer " + chapaSecretKey)
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(response -> {
                    String status = (String) ((Map) response.get("data")).get("status");
                    return paymentRepository.findByTxRef(txRef)
                            .flatMap(transaction -> {
                                transaction.setStatus(status);
                                return paymentRepository.save(transaction);
                            });
                });
    }

    public Mono<PaymentTransaction> createPayment(PaymentTransaction payment) {
        return paymentRepository.save(payment);
    }

    public Flux<PaymentTransaction> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Flux<PaymentTransaction> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Mono<Void> deletePayment(Long id) {
        return paymentRepository.deleteById(id);
    }
}
/*
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${payment.api.base-url}")
    private String baseUrl;

    @Value("${payment.api.secret-key}")
    private String chapaSecretKey;

    @Value("${payment.callback-url}")
    private String callbackUrl;

    @Value("${payment.return-url}")
    private String returnUrl;

    private final WebClient webClient;
    private final PaymentRepository paymentRepository;

    public PaymentService(WebClient.Builder webClientBuilder, PaymentRepository paymentRepository) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.paymentRepository = paymentRepository;
    }

   public Mono<String> initializePayment(PaymentTransaction paymentTransaction) {
        // Set default values for the transaction
        paymentTransaction.setCreatedAt(LocalDateTime.now());
        paymentTransaction.setStatus("pending");
        paymentTransaction.setCurrency("ETB"); // Set default currency
        paymentTransaction.setTxRef("tx-ref-" + System.currentTimeMillis()); // Generate unique tx_ref

        // Save the transaction to the database
        return paymentRepository.save(paymentTransaction)
                .flatMap(savedTransaction -> {
                    // Create the paymentData object for the Chapa API
                    Map<String, Object> paymentData = Map.of(
                            "amount", savedTransaction.getAmount(),
                            "currency", savedTransaction.getCurrency(),
                            "email", savedTransaction.getEmail(),
                            "phone_number", savedTransaction.getPhoneNumber(),
                            "tx_ref", savedTransaction.getTxRef(),
                            "callback_url", callbackUrl,
                            "return_url", returnUrl
                    );

                    // Call the Chapa API to initialize the payment
                    return webClient.post()
                            .uri("/transaction/initialize")
                            .header("Authorization", "Bearer " + chapaSecretKey)
                            .bodyValue(paymentData)
                            .retrieve()
                            .bodyToMono(Map.class)
                            .map(response -> (String) ((Map) response.get("data")).get("checkout_url"));
                });
    }

    public Mono<PaymentTransaction> verifyPayment(String txRef) {
        return webClient.get()
                .uri("/transaction/verify/" + txRef)
                .header("Authorization", "Bearer " + chapaSecretKey)
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(response -> {
                    String status = (String) ((Map) response.get("data")).get("status");
                    return paymentRepository.findByTxRef(txRef)
                            .flatMap(transaction -> {
                                transaction.setStatus(status);
                                return paymentRepository.save(transaction);
                            });
                });
    }
}

Frontend: Sends only the necessary data (email, phone number, and amount) to the backend.
Backend: Handles the creation of the paymentData object, generates the tx_ref, and calls the Chapa API.
Benefits: Simpler frontend, improved security, and centralized logic.

@PostMapping("/initialize")
public Mono<String> initializePayment(@RequestBody Map<String, Object> request) {
    // Extract fields from the request
    String email = (String) request.get("email");
    String phoneNumber = (String) request.get("phoneNumber");
    Double amount = Double.parseDouble(request.get("amount").toString());

    // Generate a unique transaction reference
    String txRef = "tx-ref-" + System.currentTimeMillis();

    // Create the paymentData object
    Map<String, Object> paymentData = new HashMap<>();
    paymentData.put("email", email);
    paymentData.put("phone_number", phoneNumber);
    paymentData.put("amount", amount);
    paymentData.put("currency", "ETB"); // Set currency to ETB
    paymentData.put("tx_ref", txRef); // Add the generated tx_ref
    paymentData.put("callback_url", "https://yourwebsite.com/callback"); // Webhook URL
    paymentData.put("return_url", "https://yourwebsite.com/return"); // Redirect URL

    // Call the Chapa API
    return WebClient.create("https://api.chapa.co/v1")
            .post()
            .uri("/transaction/initialize")
            .header("Authorization", "Bearer " + chapaSecretKey)
            .bodyValue(paymentData)
            .retrieve()
            .bodyToMono(Map.class)
            .map(response -> (String) response.get("data").get("checkout_url"));
}



*/

/*
/* private final PaymentTransactionRepository paymentRepository;

    public PaymentService(PaymentTransactionRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    private final WebClient webClient = WebClient.create("https://api.chapa.co/v1");

    public Mono<String> initializePayment(PaymentTransaction paymentTransaction) {
        paymentTransaction.setCreatedAt(LocalDateTime.now());
        paymentTransaction.setStatus("pending");

        return paymentRepository.save(paymentTransaction)
                .flatMap(savedTransaction -> webClient.post()
                        .uri("/transaction/initialize")
                        .header("Authorization", "Bearer " + chapaSecretKey)
                        .bodyValue(Map.of(
                                "amount", savedTransaction.getAmount(),
                                "currency", savedTransaction.getCurrency(),
                                "email", savedTransaction.getEmail(),
                                "phone_number", savedTransaction.getPhoneNumber(),
                                "tx_ref", savedTransaction.getTxRef(),
                                "callback_url", "https://yourwebsite.com/callback",
                                "return_url", "https://yourwebsite.com/return"
                        ))
                        .retrieve()
                        .bodyToMono(Map.class)
                        .map(response -> (String) response.get("data").get("checkout_url")));
    }

    public Mono<PaymentTransaction> verifyPayment(String txRef) {
        return webClient.get()
                .uri("/transaction/verify/" + txRef)
                .header("Authorization", "Bearer " + chapaSecretKey)
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(response -> {
                    String status = (String) response.get("data").get("status");
                    return paymentRepository.findByTxRef(txRef)
                            .flatMap(transaction -> {
                                transaction.setStatus(status);
                                return paymentRepository.save(transaction);
                            });
                });
    }
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

 import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PaymentTransactionRepository paymentTransactionRepository;

    public DataInitializer(PaymentTransactionRepository paymentTransactionRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert 10 sample payment transactions
        paymentTransactionRepository.saveAll(
                List.of(
                        new PaymentTransaction(null, "tx-ref-1", "user1@example.com", "0912345678", 100.00, "ETB", "success", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-2", "user2@example.com", "0912345679", 200.00, "ETB", "pending", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-3", "user3@example.com", "0912345680", 150.00, "ETB", "failed", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-4", "user4@example.com", "0912345681", 300.00, "ETB", "success", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-5", "user5@example.com", "0912345682", 250.00, "ETB", "pending", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-6", "user6@example.com", "0912345683", 400.00, "ETB", "success", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-7", "user7@example.com", "0912345684", 350.00, "ETB", "failed", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-8", "user8@example.com", "0912345685", 500.00, "ETB", "success", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-9", "user9@example.com", "0912345686", 450.00, "ETB", "pending", LocalDateTime.now()),
                        new PaymentTransaction(null, "tx-ref-10", "user10@example.com", "0912345687", 600.00, "ETB", "success", LocalDateTime.now())
                )
        ).subscribe(); // Save all transactions reactively
    }
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments().collectList().block(); // Block until all payments are fetched
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment).block(); // Block until payment is created
    }
}}
*/
