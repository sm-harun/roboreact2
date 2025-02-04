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
@Table("teams") // Maps this class to the "teams" table in the database
public class Team {

    @Id // Marks this field as the identifier for the entity
    private Long id;
    private String name; // Team name
    private String role; // Role of the team member
    private String email; // Email of the team member
    private String details; // Additional details
    private LocalDateTime createdAt; // Timestamp for creation
    private LocalDateTime updatedAt; // Timestamp for last update

}
