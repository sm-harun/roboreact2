package com.nas.robospring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class AuthenticationManagerConfig {

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        return authentication -> {
            String username = authentication.getName();
            String password = (String) authentication.getCredentials();

            return userDetailsService.findByUsername(username)
                    .flatMap(userDetails ->
                            passwordEncoder.matches(password, userDetails.getPassword())
                                    ? Mono.just(new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities()))
                                    : Mono.error(new RuntimeException("Invalid credentials"))
                    )
                    .cast(Authentication.class); // Ensure explicit cast to Authentication
        };
    }
}