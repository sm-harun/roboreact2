package com.nas.robospring.model;

/*import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;*/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("posts")
public class Post {

    @Id
    private Long id;

    private String title;

    private String description;

    private String content;

    private LocalDateTime postedOn;

    private Long commentId; // Foreign key to the comment table


}
/*
CommunityPost
postId (Primary Key)
userId (Foreign Key)
content
timestamp
 */
