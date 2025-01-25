package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("payment_methods")
public class PaymentMethod {
    @Id
    private Integer id;
    private String method;

    // Getters and setters...
}
/*
public enum PaymentMethod {
    CREDIT_CARD,
    PAYPAL,
    BANK_TRANSFER
}
 */
