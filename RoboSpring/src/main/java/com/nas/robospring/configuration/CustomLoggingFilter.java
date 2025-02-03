package com.nas.robospring.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Component
public class CustomLoggingFilter{
    protected Mono<Void> doFilter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("Incoming request: " + exchange.getRequest().getURI());
        return chain.filter(exchange) // Continue the chain
                .doOnSuccess(aVoid -> System.out.println("Response sent: " + exchange.getResponse().getStatusCode()));
    }



}