package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("vex_robotics")
public class VexRobotics {
    @Id
    private Long id;
    private String platformName;
    private String targetAudience;
    private String focus;
    private String components;
    private String programming;
    private String curriculum;
    private String useCase;
}
