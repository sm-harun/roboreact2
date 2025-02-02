package com.nas.robospring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationRequest {
    private String token; // Verification token sent to the user
    private double amount; // Amount to be charged
    private String paymentMethod; // Token or payment method ID

}
