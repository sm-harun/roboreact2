package com.nas.robospring.service;

import com.nas.robospring.model.TimelineEvent;
import com.nas.robospring.repository.TimelineEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TimelineEventService {
    @Autowired
    private TimelineEventRepository timelineEventRepository;

    public Flux<TimelineEvent> getAllTimelineEvents() {
        return timelineEventRepository.findAll(); // Fetch all timeline events
    }

    public Mono<TimelineEvent> createNewEvent(TimelineEvent event) {
        event.setCreatedAt(LocalDateTime.now());
        return timelineEventRepository.save(event); // Save new event
    }

    // Add other methods for updating and deleting events if necessary
}
