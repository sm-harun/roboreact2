package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("support")
public class Support {
    @Id
    private Long id;
    private String email; // Support email
    private String phone; // Support phone number
    private String description; // Description of support services

    // Getters and setters
}
