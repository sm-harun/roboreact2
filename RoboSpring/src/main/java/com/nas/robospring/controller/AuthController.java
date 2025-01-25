package com.nas.robospring.controller;

import com.nas.robospring.configuration.JwtUtil;
import com.nas.robospring.model.User;
import com.nas.robospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil; // Helper class to generate JWT
    // Sign Up
    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Password hashing should be done here before saving
        return ResponseEntity.ok(userService.registerUser(user).block());
    }

 /*   // Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String token = userService.loginUser(user.getUsername(), user.getPassword()).block();
        return ResponseEntity.ok(token);
    }*/

     /*   @PostMapping("/login2")
    public ResponseEntity<String> loginUser2(@RequestBody User user) {
        Mono<User> foundUser = userService.findByUsername(user.getUsername());

        return foundUser.flatMap(found -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(user.getPassword(), found.getPassword())) {
                String token = jwtUtil.generateToken(found); // Generate JWT token
                return Mono.just(ResponseEntity.ok(token));
            } else {
                return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
            }
        }).block(); // Block to wait for the response
    }*/
}
