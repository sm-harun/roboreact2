package com.nas.robospring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String email; // For sending verification
    private String phoneNumber; // Optional
    private double amount; // Amount to be charged
    private String paymentMethod; // Token or payment method ID

}
