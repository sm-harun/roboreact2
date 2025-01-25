package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("teacher_resources")
public class TeacherResource {
    @Id
    private Long resourceId;
    private String title;
    private String description;
    private String fileUrl; // Link to the file or resource
    private Long submittedBy; // User ID of the teacher who submitted it

    // Getters and Setters
}
