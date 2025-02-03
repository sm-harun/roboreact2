package com.nas.robospring.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Log the incoming request
        System.out.println("Incoming request: " + exchange.getRequest().getURI());

        // Continue with the chain
        return chain.filter(exchange)
                .doOnSuccess(aVoid -> System.out.println("Response sent: " + exchange.getResponse().getStatusCode()));
    }
}