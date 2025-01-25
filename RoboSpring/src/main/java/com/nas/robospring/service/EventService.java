package com.nas.robospring.service;

import com.nas.robospring.model.Event;
import com.nas.robospring.repository.EventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Mono<Event> createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Mono<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Flux<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Mono<Void> deleteEvent(Long id) {
        return eventRepository.deleteById(id);
    }
}
/*

import com.nas.robospring.model.Event;
import com.nas.robospring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Flux<Event> getAllEvents() {
        return eventRepository.findAll(); // Fetch all events
    }

    public Mono<Event> createEvent(Event event) {
        return eventRepository.save(event); // Create a new event
    }

    public Mono<Event> updateEvent(Long id, Event eventDetails) {
        return eventRepository.findById(id)
                .flatMap(event -> {
                    event.setTitle(eventDetails.getTitle());
                    event.setDescription(eventDetails.getDescription());
                    event.setEventDate(eventDetails.getEventDate());
                    event.setLocation(eventDetails.getLocation());
                    return eventRepository.save(event);
                });
    }

    public Mono<Void> deleteEvent(Long id) {
        return eventRepository.deleteById(id); // Delete event by ID
    }
}
*/
