package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("payments")
public class Payment {
    @Id
    private Long id;
    private BigDecimal amount; // Amount of the payment
    private String paymentMethod; // e.g., CARD, BANK_TRANSFER
    private String status; // e.g., SUCCESS, FAILED, PENDING
    private LocalDateTime createdAt;

    private Long userId; // FK to link the payment to a user
}
/*
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // Amount of the payment
    private String paymentMethod; // e.g., CARD, BANK_TRANSFER
    private String status; // e.g., SUCCESS, FAILED, PENDING
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Link to the user who made the payment

    // Getters and Setters
}
 */
