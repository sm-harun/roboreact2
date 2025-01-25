package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("applications")
public class Application {
    @Id
    private Long id;
    private String name;
    private String email;
    private String message;

    // Getters and setters
}
