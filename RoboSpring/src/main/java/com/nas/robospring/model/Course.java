package com.nas.robospring.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("courses")
public class Course {
    @Id
    private Long id;
    private String title;
    private String description;
    private String level; // e.g., Low Grade, High School, College, University
    private Integer duration; // Duration in hours
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
/*
    import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String level; // e.g., Low Grade, High School, College, University
    private Integer duration; // Duration in hours

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and Setters
}
     */
//    @Id
//    private Long courseId;
//    private String title;
//    private String description;
//    private String category; // e.g., "Robotics"
//    private String level; // e.g., "Beginner", "Intermediate"
//    private String content; // Description of the course content

    // Getters and Setters
}
