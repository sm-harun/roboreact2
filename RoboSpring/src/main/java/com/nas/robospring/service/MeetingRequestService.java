package com.nas.robospring.service;

import com.nas.robospring.model.MeetingRequest;
import com.nas.robospring.repository.MeetingRequestRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MeetingRequestService {
    private final MeetingRequestRepository requestRepository;

    public MeetingRequestService(MeetingRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Mono<MeetingRequest> createRequest(MeetingRequest request) {
        return requestRepository.save(request);
    }

    public Flux<MeetingRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // Add logic for updating the status of a request
}
