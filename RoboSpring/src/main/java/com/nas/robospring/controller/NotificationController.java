package com.nas.robospring.controller;

import com.nas.robospring.model.Notification;
import com.nas.robospring.service.NotificationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Mono<Notification> createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @GetMapping("/user/{userId}")
    public Flux<Notification> getNotificationsByUserId(@PathVariable Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @PatchMapping("/{id}/read")
    public Mono<Void> markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteNotification(@PathVariable Long id) {
        return notificationService.deleteNotification(id);
    }
    @GetMapping("/api/notifications")
    public Flux<Notification> getAllNotifications() {
        return notificationService.getPendingNotifications();  // Or fetch all notifications if desired
    }
}
/*

import com.nas.robospring.model.Notification;
import com.nas.robospring.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    // Fetch notifications for a specific user
    @GetMapping("/user/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {
        return notificationService.getNotifications(userId).collectList().block(); // Block until notifications are fetched
    }

    // Create a new notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification).block(); // Block until notification is created
    }
}
*/
