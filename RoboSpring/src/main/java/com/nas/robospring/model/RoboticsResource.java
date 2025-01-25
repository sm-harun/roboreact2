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
@Table("robotics_resources")
public class RoboticsResource {

    @Id
    private Long id;

    private String title;

    /*   @ManyToOne // Assuming there's a Category class to represent the categories table
       @JoinColumn(name = "category_id", referencedColumnName = "category_id")*/
    private String category; // This is the associated category

    private String type; // e.g., "Pre-built Robot", "Hands-on Activities"
    private String content; // Content description
    private String level;
    // e.g., "Pre-K", "Elementary", "Middle School"
}
