package com.nas.robospring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("notifications")
public class Notification {
    @Id
    private Long id;
    private String type; // e.g., competition, pricing, discount, application
    private Long userId; // FK to link to the user
    private String message; // Notification message
    private boolean isSent; // Status of the notification
    private boolean isRead; // Status of the notification

    private LocalDateTime scheduledTime;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSent(boolean b) {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setIsRead(boolean b) {
        isRead = b;
    }
}
/*

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Link to the user

    private String message; // Notification message
    private boolean isRead; // Status of the notification
    private LocalDateTime createdAt;

    // Getters and Setters
}
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("notifications")
public class Notification {
    @Id
    private Long notificationId;
    private Long userId; // Reference to User
    private String message;
    private boolean isRead; // Status of the notification
    private Date timestamp;

    // Getters and Setters
}
 */
