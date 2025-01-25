package com.nas.robospring.controller;

import com.nas.robospring.model.TimelineEvent;
import com.nas.robospring.service.TimelineEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/timeline")
public class TimelineEventController {
    @Autowired
    private TimelineEventService timelineEventService;

    @GetMapping
    public Flux<TimelineEvent> getAllTimelineEvents() {
        return timelineEventService.getAllTimelineEvents(); // Fetch all events
    }

    @PostMapping
    public Mono<TimelineEvent> createEvent(@RequestBody TimelineEvent event) {
        return timelineEventService.createNewEvent(event); // Create a new event
    }

    // Add methods for updating and deleting events as needed
}
