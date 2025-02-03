package com.nas.robospring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ReactiveUserDetailsService userDetailsService; // Your custom user details service

    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = getJWTFromRequest(exchange.getRequest());

        if (token != null && tokenProvider.validateToken(token)) {
            String username = tokenProvider.getUsernameFromJWT(token); // Extract username from the token

            // Load user details reactively
            return userDetailsService.findByUsername(username)
                    .flatMap(userDetails -> {
                        // Build an authentication token
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authenticationToken));
                    });
        }
                        /*
                        // Set authentication in the SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        return chain.filter(exchange); // Continue processing the exchange
                    })
                    .switchIfEmpty(Mono.error(new RuntimeException("User not found"))); // Handle not found user case
        }
*/

        return chain.filter(exchange); // Continue filter chain if no valid token is present
    }

    private String getJWTFromRequest(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Extract token without "Bearer "
        }
        return null;
    }
}
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ReactiveUserDetailsService userDetailsService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // Retrieve token from the Authorization header
        String token = getJWTFromRequest(request);

        if (token != null && tokenProvider.validateToken(token)) {
            String username = tokenProvider.getUsernameFromJWT(token);

            return userDetailsService.findByUsername(username)
                    .flatMap(userDetails -> {
                        // Set authentication in SecurityContext
                        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        // Continue with the next filter in the chain
                        return chain.filter(exchange);
                    })
                    .switchIfEmpty(Mono.empty()) // Handle case where user is not found
                    .then(); // Return void Mono after processing
        }

        // If no valid token, simply proceed without setting the authentication
        return chain.filter(exchange);
    }

    private String getJWTFromRequest(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
}*/
/*
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ReactiveUserDetailsService customUserDetailsService; // Use ReactiveUserDetailsService

    @Override
    protected void doFilterInternal(ServerWebExchange exchange, FilterChain chain) throws ServletException, IOException {
        ServerHttpRequest request = exchange.getRequest();

        // Get JWT from the request
        String token = getJWTfromRequest(request);
        if (token != null && tokenProvider.validateToken(token)) {
            String username = tokenProvider.getUsernameFromJWT(token);

            // Load user details reactively
            customUserDetailsService.findByUsername(username)
                .flatMap(userDetails -> {
                    UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Set Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    return Mono.just(authenticationToken);
                })
                .subscribe(); // Subscribe to set the authentication in the context
        }

        // Proceed with the filter chain
        chain.doFilter(exchange);
    }

    private String getJWTfromRequest(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private ReactiveUserDetailsService userDetailsService; // Assume this is your reactive user details service

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Retrieve JWT from request
        String token = getJWTFromRequest(request);

        if (token != null && tokenProvider.validateToken(token)) {
            // Extract the username from the token
            String username = tokenProvider.getUsernameFromJWT(token);

            // Load user details reactively
            userDetailsService.findByUsername(username)
                    .flatMap(userDetails -> {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        // Set the details for the authentication token
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // Set the authentication in the SecurityContext
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        return Mono.just(authenticationToken);
                    })
                    .switchIfEmpty(Mono.error(new RuntimeException("User not found"))) // Handle user not found scenario
                    .subscribe(); // Subscribe to the Mono (you might need to handle this differently in a real application)
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}*/
