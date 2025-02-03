package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("competitions") // Specify the table name
public class Competition {

    @Id
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate startDate;
    private Double price; // Can be null to indicate "Call for price"
    // private LocalDate registrationDeadline;
//    private LocalDateTime dueDate; // Due date for the competition
    private LocalDateTime dueDate; // Due date for the competition
    private Long userId; // Reference to the user who registered the competition
    private String website; // Link to the competition website

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
/*
    userId (FK) references User(userId) (to track who registered)

    competitionId (Primary Key)
name
description
date
location
registrationDeadline
     */
}

