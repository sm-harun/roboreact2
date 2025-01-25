package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("feedback")
public class Feedback {
    @Id
    private Long id;

    private Long userId;   // Store user ID for whom the feedback is given
    private Long courseId; // Eventual FK, adjust for resources or services as needed
    private String message;
    private Integer rating; // Rating from 1 to 5
    private LocalDateTime createdAt;
}
/*
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Feedback given by the user

    @ManyToOne
    @JoinColumn(name = "course_id") // Feedback linked to a specific course
    private Course course;

    private String message;
    private Integer rating; // Rating from 1 to 5
    private LocalDateTime createdAt;

    // Getters and Setters
}
 */
/*
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("feedback")
public class Feedback {
    @Id
    private Long feedbackId;
    private Long userId; // Reference to User
    private Long resourceId; // Reference to Resource
    private String comments;
    private int rating; // Rating out of 5

    // Getters and Setters
}
*/
