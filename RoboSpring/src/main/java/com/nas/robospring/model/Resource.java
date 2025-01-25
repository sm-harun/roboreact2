package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("resources")
public class Resource {
    @Id
    private Long id;
    private String title;
    private String description;
    private String link; // URL to the resource
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
/*
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String link; // URL to the resource

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
}
 */
/*
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("resources")
public class Resource {
    @Id
    private Integer id;
    private String title;
    private String type; // e.g., article, video, document
    private String content;
    private GradeType gradeLevel;*/
  //  private String gradeLevel; // e.g., Pre-K, Elementary
   // private String category; // e.g., "Robotics", "Programming", etc.
    /*
        private Long resourceId;
    private String title;
    private String type; // e.g., article, video, document
    private String content;
    private String gradeLevel; // e.g., Pre-K, Elementary
    private String category; // e.g., "Robotics", "Programming", etc.
    private Boolean isFeatured;

    resourceId (Primary Key)
title
type (e.g., article, video, document)
content
gradeLevel (e.g., Pre-K, Elementary, etc.)
     */


