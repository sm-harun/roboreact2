package com.nas.robospring.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("studentRegistrations")
public class StudentRegistration {
    private String studentName;
    private String email;
    private String branch;
    private double amount; // Amount to be charged
    private String paymentMethod; // This will hold the payment method/token

}

