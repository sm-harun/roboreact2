package com.nas.robospring.repository;

import com.nas.robospring.model.MeetingRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MeetingRequestRepository extends ReactiveCrudRepository<MeetingRequest, Long> {

}
