package com.nas.robospring.repository;

import com.nas.robospring.model.Notification;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends ReactiveCrudRepository<Notification, Long> {
 //   Flux<Notification> findByUser_Id(Long userId); // Fetch notifications for a specific user
 Flux<Notification> findByUserId(Long userId);
 Flux<Notification> findByScheduledTimeBeforeAndIsSent(LocalDateTime now, Boolean issent);
}
/*
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser_Id(Long userId); // Fetch notifications for a specific user
}
 */
