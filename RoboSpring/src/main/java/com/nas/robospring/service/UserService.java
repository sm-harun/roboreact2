package com.nas.robospring.service;

import com.nas.robospring.model.Order;
import com.nas.robospring.model.User;
import com.nas.robospring.repository.OrderRepository;
import com.nas.robospring.repository.UserRepository;
import com.nas.robospring.configuration.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Import to hash passwords
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil; // JWT utility for generating tokens

    public Mono<User> registerUser(User user) {
        // Hash the password before saving
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public Mono<String> login(String username, String password) {
        return userRepository.findByUsername(username)
                .flatMap(user -> {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    if (encoder.matches(password, user.getPassword())) {
                        String token = jwtUtil.generateToken(user); // Generate JWT
                        return Mono.just(token);
                    } else {
                        return Mono.error(new RuntimeException("Invalid credentials"));
                    }
                });
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }
    public Mono<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
        // Get all users
    }

}
/*
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Mono<User> registerUser(User user) {
        return userRepository.save(user); // Save user to the database
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username); // Find user by username
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll(); // Get all users
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id); // Delete user by ID
    }
}

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public Flux<Order> fetchUserOrders(Integer userId) {
        return orderRepository.findByUserId(userId); // Fetch orders for the specified user ID
    }
   *//* @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .block();  // Blocking for demonstration purposes; adapt for reactive context
    }*//*
    public Mono<User> createUser(User user) {
        // You may want to hash the password before saving
        return userRepository.save(user);
    }

    public Mono<User> login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password)) // In practice, use hash comparison
                .switchIfEmpty(Mono.empty());
    }
    public Mono<User> checkUserCredentials(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password)); // In practice, use hashed password comparison
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }


}*/
/*import com.nas.restaurantapp.model.User;
import com.nas.restaurantapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
}*/
/*

import com.nas.restaurantapp.configuration.UserLoginCommand;
import com.nas.restaurantapp.configuration.UserRegisterCommand;
import com.nas.restaurantapp.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> login(UserLoginCommand request);
    Mono<User> create(UserRegisterCommand userDto);
    Mono<User> retrieve(int userId);
    Mono<User> loginFromToken(String email);
}
*/
