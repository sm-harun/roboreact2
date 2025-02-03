package com.nas.robospring.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<String>> handleIllegalArgument(IllegalArgumentException ex) {
        return Mono.just(ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
