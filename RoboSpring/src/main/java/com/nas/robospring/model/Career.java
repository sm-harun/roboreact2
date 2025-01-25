package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("careers")
public class Career {
    @Id
    private Long id;
    private String title;
    private String description;
    private String applicationLink; // Link for applications

    // Getters and setters
}
