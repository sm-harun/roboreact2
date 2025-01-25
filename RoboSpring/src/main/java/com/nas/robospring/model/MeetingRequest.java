package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("meeting_requests")
public class MeetingRequest {
    @Id
    private Long requestId;
    private String requesterName;
    private Date requestedDate;
    private String purpose;
    private String contactInfo;
    private String status; // e.g., "PENDING", "APPROVED", "DECLINED"

    // Getters and Setters
}
