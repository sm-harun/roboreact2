package com.nas.robospring.controller;


import com.nas.robospring.model.Event;
import com.nas.robospring.service.EventService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Mono<Event> createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    public Mono<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    public Flux<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}

/*
import com.nas.robospring.model.Event;
import com.nas.robospring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public Flux<Event> getAllEvents() {
        return eventRepository.findAll(); // Call to fetch all events for the timeline
    }
}
*/
