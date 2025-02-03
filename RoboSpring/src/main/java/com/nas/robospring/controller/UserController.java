package com.nas.robospring.controller;
import com.nas.robospring.configuration.CustomUserDetails;
import com.nas.robospring.configuration.JwtUtil;
import com.nas.robospring.dto.JWTAuthResponse;
import com.nas.robospring.dto.LoginDto;
import com.nas.robospring.model.UserWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.nas.robospring.dto.SignUpDto;
import com.nas.robospring.model.Course;
import com.nas.robospring.model.Order;
import com.nas.robospring.model.User;
import com.nas.robospring.service.CourseService;
import com.nas.robospring.service.OrderService;
import com.nas.robospring.service.UserService;
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private  CourseService courseService;
    @Autowired
    private  OrderService orderService;

    @GetMapping("/{id}/with-roles") // Define the endpoint for fetching user with roles
    public Mono<ResponseEntity<UserWithRoles>> getUserWithRoles(@PathVariable Long id) {
        return userService.findUserWithRoles(id) // Call service method
                .map(userWithRoles -> ResponseEntity.ok(userWithRoles)) // Return user with roles
                .defaultIfEmpty(ResponseEntity.notFound().build()); // 404 if not found
    }

    @GetMapping("/{userId}/enrolled-courses")
    public Flux<Course> getEnrolledCourses(@PathVariable Long userId) {
        return courseService.getCoursesByUserId(userId);
    }

    @GetMapping("/{userId}/order-history")
    public Flux<Order> getOrderHistory(@PathVariable Long userId) {
        return orderService.getOrderHistoryByUserId(Math.toIntExact(userId));
    }
/*
    @PostMapping("/signup")
    public Mono<ResponseEntity<User>> createUser(@RequestBody SignUpDto signUpDto) {
        return userService.registerUser(signUpDto)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<JWTAuthResponse>> authenticateUser(@RequestBody LoginDto loginDto) {
        return userService.authenticateUser(loginDto.getUsername(), loginDto.getPassword())
                .map(token -> ResponseEntity.ok(new JWTAuthResponse(token)))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new JWTAuthResponse("Invalid username or password"))));
    }*/

    @GetMapping("/check/me")
    public Mono<ResponseEntity<CustomUserDetails>> checkUserAuthentication(@AuthenticationPrincipal CustomUserDetails user) {
        if (user != null) {
            return Mono.just(ResponseEntity.ok(user));
        } else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/username/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public Mono<User> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
/*import com.nas.robospring.dto.JWTAuthResponse;
import com.nas.robospring.dto.LoginRequest;
import com.nas.robospring.dto.SignUpDto;
import com.nas.robospring.model.Course;
import com.nas.robospring.model.Order;
import com.nas.robospring.model.User;
import com.nas.robospring.service.CourseService;
import com.nas.robospring.service.OrderService;
import com.nas.robospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private JwtTokenProvider tokenProvider;
    private final UserService userService;
    private final CourseService courseService;
    private final OrderService orderService;
   // private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, CourseService courseService, OrderService orderService)
                        //  AuthenticationManager authenticationManager)
     {
        this.userService = userService;
        this.courseService = courseService;
        this.orderService = orderService;
       // this.authenticationManager = authenticationManager;
    }

    @GetMapping("/{userId}/enrolled-courses")
    public Flux<Course> getEnrolledCourses(@PathVariable Long userId) {
        return courseService.getCoursesByUserId(userId); // Fetching courses for the user
    }

    @GetMapping("/{userId}/order-history")
    public Flux<Order> getOrderHistory(@PathVariable Integer userId) {
        return orderService.getOrderHistoryByUserId(userId); // Fetching order history for the user
    }
    @PostMapping("/signup")
    public Mono<ResponseEntity<User>> createUser(@RequestBody SignUpDto signUpDto) {
        return UserService.registerUser(signUpDto)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)));
    }


    @PostMapping("/login")
    public Mono<ResponseEntity<JWTAuthResponse>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return UserService.authenticateUser(loginRequest) // Assuming authenticateUser method is implemented in AuthService
                .map(token -> ResponseEntity.ok(new JWTAuthResponse(token)))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new JWTAuthResponse("Invalid username or password"))));
    //public Mono<ResponseEntity<String>> loginUser(@RequestBody LoginRequest loginRequest) {

//                .map(foundUser -> ResponseEntity.ok("Login Successful")) // Make sure this returns a success message
//                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        //  .map(user -> "Login successful!") // Customize this as needed
        //    .switchIfEmpty(Mono.error(new Exception("Login failed!"))); // Handle failed login attempts
    }

    @GetMapping("/check/me") // Endpoint to check current user's authentication status
    public Mono<ResponseEntity<User>> checkUserAuthentication(@AuthenticationPrincipal User user) {
        if (user != null) {
            return Mono.just(ResponseEntity.ok(user)); // Return authenticated user's information
        } else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
            // If no user is found, return unauthorized response
        }
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public Mono<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
  @GetMapping("/username/{username}")
    public Mono<User> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public Mono<User> findByEmail(String email) {
        return userService.findByEmail(email);
    }
}*/
/*    @PostMapping("/register")
    public Mono<User> createUser(@RequestBody User user) {
        return userService.registerUser(user);
               *//* .doOnSuccess(savedUser -> {
                    // Logic to log or handle the newly created user if necessary
                });*//*
    }*/
/*    @PostMapping("/login")
    public Mono<ResponseEntity<String>> loginUser(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return Mono.just(ResponseEntity.badRequest().body("Username and password must not be empty."));
        }

        return userService.login(loginRequest.getUsername(), loginRequest.getPassword())
                .map(token -> ResponseEntity.ok(token))
                .onErrorResume(e -> {
                    // Handle specific exceptions if needed
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials."));
                });*/
/*@PostMapping("/login")  // Endpoint for user login
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest loginRequest) {
        return Mono.fromCallable(() -> {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Return some meaningful response, e.g., success message or token
            return ResponseEntity.ok("Login successful!");
        }).onErrorReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed")); // Handle login failures
    }
*/
   /* @PostMapping("/login")
    public Mono<ResponseEntity<String>> loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.getEmail(), loginRequest.getPassword())
                .map(foundUser -> ResponseEntity.ok("Login Successful")) // Make sure this returns a success message
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
              //  .map(user -> "Login successful!") // Customize this as needed
            //    .switchIfEmpty(Mono.error(new Exception("Login failed!"))); // Handle failed login attempts
    }*/

 /*   @GetMapping("/me") // It should be a GET mapping
    public ResponseEntity<?> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Here, we could return user info if needed
            return ResponseEntity.ok(authentication.getPrincipal()); // This could be a User object or relevant info
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }*/
/* @PostMapping("/login-check") // New endpoint for checking user credentials
 public Mono<ResponseEntity<String>> loginCheck(@RequestBody LoginRequest loginRequest) {
     return userService.checkUserCredentials(loginRequest.getEmail(), loginRequest.getPassword())
             .map(user -> ResponseEntity.ok("User exists!"))
             .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
 }*/

/*
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")  // Endpoint for user login
    public Mono<ResponseEntity<String>> login(@RequestBody LoginRequest loginRequest) {
        return Mono.fromCallable(() -> {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Return some meaningful response, e.g., success message or token
            return ResponseEntity.ok("Login successful!");
        }).onErrorReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed")); // Handle login failures
    }

    @GetMapping("/me")  // Check user authentication status
    public Mono<ResponseEntity<User>> checkUserAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal(); // Cast to your User class
            return Mono.just(ResponseEntity.ok(user)); // Return user details
        } else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)); // Unauthorized response
        }
    }
}
@GetMapping("/me") // It should be a GET mapping
    public ResponseEntity<?> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Here, we could return user info if needed
            return ResponseEntity.ok(authentication.getPrincipal()); // This could be a User object or relevant info
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


, "An expected CSRF token cannot be found," indicates that your Spring Boot application is enforcing CSRF (Cross-Site Request Forgery) protection and is expecting a valid CSRF token to be present in the request. This usually occurs for POST, PUT, and DELETE requests.

Understanding CSRF Protection
CSRF protection is a security feature in Spring Boot to prevent unauthorized commands from being transmitted from a user that the web application trusts. When CSRF protection is enabled, most HTTP requests (except GET requests) must provide a CSRF token to proceed.

Solutions to Resolve the CSRF Token Error
1. Disable CSRF Protection (Not Recommended for Production)
If you're in a development environment and want to quickly bypass this error, you can disable CSRF protection for your application. This approach is not recommended for production use, as it will expose your application to CSRF attacks.

You can disable it by modifying your SecurityConfig class:

java
Copy
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf().disable()  // Disable CSRF for development (not recommended for production)
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .pathMatchers(HttpMethod.GET, "/api/**").permitAll()
            .pathMatchers("/api/register", "/api/login").permitAll()
            .anyExchange().authenticated();

        return http.build();
    }
}
2. Include CSRF Token in Requests (Recommended)
A better approach, especially for production, is to send the CSRF token in your requests. If you want to maintain CSRF protection:

For Web: Make sure your frontend sends the CSRF token with every request, typically in the headers.
Add CSRF Token Handling in the Backend:

java
Copy
// Add this in your security configuration
http.csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse());
Pass the CSRF Token from Frontend:
In a React application (or whatever front end you are using), you might need to obtain the CSRF token from a cookie or the response headers and include it in your POST requests.

You can do this by modifying your API requests:

javascript
Copy
import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

// To setup CSRF token in axios
axios.defaults.withCredentials = true; // Make sure to include credentials

// Example of a function that sends a request with CSRF token
export const createUser = async (userData) => {
  try {
    const response = await axios.post(`${API_URL}/register`, userData, {
      headers: {
        'X-CSRF-TOKEN': csrfToken, // Replace with the actual token retrieved from cookies/response
      },
    });
    return response.data;
  } catch (error) {
    throw error.response.data; // Handle error appropriately
  }
};
Step 3: Verify CSRF Configuration
Check that you don’t have any misconfigurations in your Spring Security settings. In a typical setup for REST APIs using Spring Security, it’s important to carefully configure what requires CSRF protection.

Additional Resources
Spring Security Reference: For more about Spring Security and CSRF protection, refer to the Spring Security documentation.
Security Best Practices: Always consider security implications when making changes related to CSRF or any security settings.
Conclusion
Choose an appropriate approach to handle CSRF based on your application's needs. Disabling CSRF protection can be an easy fix during development but is not advisable for a production environment. Instead, handle the CSRF tokens properly for secure communication
 */

/*@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }
}*/
/*import com.nas.reactproject.configuration.UserLoginCommand;
import com.nas.reactproject.configuration.UserRegisterCommand;
import com.nas.reactproject.model.User;
import com.nas.reactproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

import javax.xml.validation.ValidatorHandler;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/users") // Defining the base URL for the User endpoints
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ValidatorHandler validatorHandler;

    @PostMapping("/register")
    public Mono<ResponseEntity<User>> createUser(@RequestBody UserRegisterCommand userRegisterCommand) {
        validatorHandler.validate(userRegisterCommand);
        return userService.create(userRegisterCommand)
                .doOnSuccess(userSaved -> log.info("User saved with id: {} ", userSaved.getId()))
                .map(user -> ResponseEntity.created(getToUri(user)).body(user))
                .onErrorResume(e -> {
                    log.error("Error in createUser method", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> loginUser(@RequestBody UserLoginCommand userLoginCommand) {
        return userService.login(userLoginCommand)
                .map(foundUser -> ResponseEntity.ok("Login Successful"))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    private URI getToUri(User userSaved) {
        return UriComponentsBuilder.fromPath("/{id}")
                .buildAndExpand(userSaved.getId()).toUri();
    }
}*/
/*
import com.nas.reactproject.configuration.JwtUtil;
import com.nas.reactproject.model.User;
import com.nas.reactproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8081") // Allow the frontend URL
public class UserController {

    //@Autowired
    private final UserService userService;
  //  @Autowired
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {


        User user = userService.authenticateUser(loginUser.getUsername(), loginUser.getPassword());
        if (user != null) {
            //return ResponseEntity.ok("Login successful"); // You can return user details or token
            String token = jwtUtil.generateToken(user.getUsername()); // Generate token for this user
            return ResponseEntity.ok(token); // Return the token as simple text response


        } else {
            return ResponseEntity.status(401).body("Login failed: Invalid username or password");
        }
    }

    // Endpoint to register a new user (optional)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User newUser) {
        userService.registerUser(newUser.getUsername(), newUser.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

}


 /*   @PostMapping("/login")
    public ResponseEntity<String> login(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            // Extract the credentials from the header
            String base64Credentials = authHeader.substring("Basic ".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials));
            // credentials = username:password
            final String[] values = credentials.split(":", 2);
            String username = values[0];
            String password = values[1];

            User user = userService.authenticateUser(username, password);
            if (user != null) {
                return ResponseEntity.ok("Login successful"); // Handle successful login
            } else {
                return ResponseEntity.status(401).body("Login failed: Invalid username or password");
            }
        } else {
            return ResponseEntity.status(401).body("Login failed: Missing Authorization header");
        }
    }*/

  /*  @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User foundUser = userService.findByUsername(user.getUsername());

        // Here, you should compare the hashed password and return an appropriate response
        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        }

        return ResponseEntity.status(401).build(); // Unauthorized
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.save(user);
        return ResponseEntity.ok(newUser);
    }*/
/*   response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081"); // Set CORS header
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");*/
