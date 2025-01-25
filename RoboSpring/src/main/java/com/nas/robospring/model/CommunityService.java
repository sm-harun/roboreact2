package com.nas.robospring.model;

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
@Table("community_services")
public class CommunityService {
    @Id
    private Long id;
    private String title; // Title of the community service
    private String description; // Description of the service
    private String contactInfo; // Contact information for more details
    private String location; // Physical location or online link if applicable

    // Getters and setters
}
