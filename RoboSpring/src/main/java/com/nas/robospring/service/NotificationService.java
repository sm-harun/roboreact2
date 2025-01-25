package com.nas.robospring.service;

import com.nas.robospring.model.Notification;
import com.nas.robospring.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Mono<Notification> createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
    public Flux<Notification> getPendingNotifications() {
        return notificationRepository.findByScheduledTimeBeforeAndIsSent(LocalDateTime.now(), false);
    }

    public Flux<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public Mono<Void> markAsRead(Long id) {
        return notificationRepository.findById(id)
                .flatMap(notification -> {
                    notification.setIsRead(true);
                    return notificationRepository.save(notification).then();
                });
    }
    public void markAsSent(Notification notification) {
        notification.isSent(true);
        notificationRepository.save(notification);
    }
    public Mono<Void> deleteNotification(Long id) {
        return notificationRepository.deleteById(id);
    }
}
/*
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class NotificationScheduler {

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 60000)  // Every minute
    public void sendNotifications() {
        Flux<Notification> pendingNotifications = notificationService.getPendingNotifications();
        pendingNotifications
                .flatMap(notification -> {
                    return notificationService.markAsSent(notification);
                })
                .subscribe();
    }
}
// Implement your notification sending logic here
// e.g., send push notifications
// After sending, mark the notification as sent


import com.nas.robospring.model.Notification;
import com.nas.robospring.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Mono<Notification> createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Flux<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public Mono<Void> markAsRead(Long id) {
        return notificationRepository.findById(id)
                .flatMap(notification -> {
                    notification.setIsRead(true);
                    return notificationRepository.save(notification).then();
                });
    }

    public Mono<Void> deleteNotification(Long id) {
        return notificationRepository.deleteById(id);
    }
}
*/


/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Mono<Notification> createNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    public Flux<Notification> getNotifications(Long userId) {
        return Flux.fromIterable(notificationRepository.findByUser_Id(userId));
    }
}*/
