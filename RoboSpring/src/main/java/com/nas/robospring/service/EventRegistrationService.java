package com.nas.robospring.service;
import com.nas.robospring.model.Event;
import com.nas.robospring.model.EventRegistration;
import com.nas.robospring.repository.EventRegistrationRepository;
import com.nas.robospring.repository.EventRepository;
import com.nas.robospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class EventRegistrationService {

    @Autowired
    private final EventRegistrationRepository registrationRepository; // Your repository for event registrations

    @Autowired
    private final UserRepository userRepository; // Assuming you have a user repository

    @Autowired
    private final EventRepository eventRepository; // Assuming you have an event repository

    public EventRegistrationService(EventRegistrationRepository registrationRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public Mono<EventRegistration> registerForEvent(Long userId, Long eventId) {
        EventRegistration registration = new EventRegistration();
        registration.setUserId(userId);
        registration.setEventId(eventId);
        registration.setRegisteredAt(LocalDateTime.now());

        return registrationRepository.save(registration);
    }

    public Flux<EventRegistration> getRegistrationsByUserId(Long userId) {
        return registrationRepository.findByUserId(userId);
    }

    public Flux<Event> getRegisteredEvents(Long userId) {
        // Fetch all registrations for the user
        return getRegistrationsByUserId(userId)
                .flatMap(registration -> eventRepository.findById(registration.getEventId()));
    }

    // Additional methods can be added as needed...
}
/*

import com.nas.robospring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EventRegistrationService {
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private EventRepository eventRepository;

    public Mono<EventRegistration> registerUserForEvent(Long eventId, Long userId) {
        return eventRepository.findById(eventId)
                .flatMap(event -> {
                    EventRegistration registration = new EventRegistration();
                    registration.setUser(new User(userId)); // Assume you have appropriate constructors
                    registration.setEvent(event);
                    registration.setRegisteredAt(LocalDateTime.now());
                    return eventRegistrationRepository.save(registration);
                });
    }
}
*/
