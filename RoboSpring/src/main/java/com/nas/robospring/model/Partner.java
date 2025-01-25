package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("partners")
public class Partner {
    @Id
    private Long id;
    private String name;
    private String description;
    private String website; // URL to the partner's website

    // Getters and setters
}
