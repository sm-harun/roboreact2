package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("clients") // Maps this class to the "clients" table in the database
public class Client {

    @Id // Marks this field as the identifier for the entity
    private Long id;
    private String name; // Client name
    private String email; // Client email
    private String details; // Additional details about the client
    private LocalDateTime createdAt; // Timestamp for creation
    private LocalDateTime updatedAt; // Timestamp for the last update

    // Getters and Setters (optional: you can use Lombok @Getter/@Setter annotations if preferred)
}
