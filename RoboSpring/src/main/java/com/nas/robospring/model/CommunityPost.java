package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("community_posts")
public class CommunityPost {
    @Id
    private Long postId;
    private Long userId; // Reference to User
    private String title;
    private String content;
    private Date timestamp;

    // Getters and Setters
}
