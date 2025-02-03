package com.nas.robospring.configuration;
import com.nas.robospring.dto.JWTAuthResponse;
import com.nas.robospring.dto.LoginDto;
import com.nas.robospring.dto.SignUpDto;
import com.nas.robospring.model.User;
import com.nas.robospring.model.UserWithRoles;
import com.nas.robospring.repository.RoleRepository;
import com.nas.robospring.repository.UserRepository;
import com.nas.robospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public Mono<ResponseEntity<User>> registerUser(@RequestBody SignUpDto signUpDto) {
        // Check for existing username or email in one reactive chain
        return userRepository.existsByUsername(signUpDto.getUsername())
                .zipWith(userRepository.existsByEmail(signUpDto.getEmail()), (usernameExists, emailExists) -> {
                    if (usernameExists) {
                        throw new IllegalArgumentException("Username already exists.");
                    }
                    if (emailExists) {
                        throw new IllegalArgumentException("Email already exists.");
                    }
                    return true; // Both checks pass
                })
                .flatMap(result -> {
                    // Create user if checks passed
                    User user = new User();
                    user.setUsername(signUpDto.getUsername());
                    user.setEmail(signUpDto.getEmail());
                    user.setPassword(passwordEncoder.encode(signUpDto.getPassword())); // Hash the password
                    return userRepository.save(user)
                            .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED).body(savedUser));
                })
                .onErrorResume(IllegalArgumentException.class, e ->
                        Mono.just(ResponseEntity.badRequest().body(null)) // Handle potential errors gracefully
                );
    }

    @PostMapping("/login") // Login endpoint
    public Mono<ResponseEntity<JWTAuthResponse>> authenticateUser(@RequestBody LoginDto loginDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()))
                .flatMap(authentication -> {
                    // Logic to create and return JWT token
                    String token = tokenProvider.generateToken(authentication);
                    return Mono.just(ResponseEntity.ok(new JWTAuthResponse(token)));
                })
                .onErrorResume(AuthenticationException.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new JWTAuthResponse("Invalid username or password"))));
    }

    @GetMapping("/user/{username}")
    public Mono<ResponseEntity<UserWithRoles>> getUserWithRoles(@PathVariable String username) {
        return userService.findUserWithRolesByUsername(username)
                .map(userWithRoles -> ResponseEntity.ok(userWithRoles))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}
/*import com.nas.robospring.dto.JWTAuthResponse;
import com.nas.robospring.dto.LoginDto;
import com.nas.robospring.dto.SignUpDto;
import com.nas.robospring.model.User;
import com.nas.robospring.model.UserWithRoles;
import com.nas.robospring.repository.RoleRepository;
import com.nas.robospring.repository.UserRepository;
import com.nas.robospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService; // Your service for dealing with users

    @Autowired
    private ReactiveAuthenticationManager authenticationManager; // Change to ReactiveAuthenticationManager

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public Mono<ResponseEntity<JWTAuthResponse>> authenticateUser(@RequestBody LoginDto loginDto, ServerWebExchange exchange) {
        return userService.authenticateUser(loginDto.getUsername(), loginDto.getPassword())
                .flatMap(token -> {
                    // Use the token to extract user details
                    return userService.findByUsername(loginDto.getUsername()) // This should return Mono<CustomUserDetails>
                            .flatMap(customUserDetails -> {
                                // Set the SecurityContext in the session
                                return exchange.getSession().doOnSuccess(session -> {
                                    SecurityContextImpl securityContext = new SecurityContextImpl();
                                    // Create authentication token with CustomUserDetails
                                    securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities()));
                                    session.getAttributes().put("SPRING_SECURITY_CONTEXT", securityContext); // Store SecurityContext in session
                                }).then(Mono.just(ResponseEntity.ok(new JWTAuthResponse(token)))); // Return response
                            });
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JWTAuthResponse("Invalid username or password"))));
    }
    @PostMapping("/signup")
    public Mono<ResponseEntity<User>> registerUser(@RequestBody SignUpDto signUpDto) {
        return userRepository.existsByUsername(signUpDto.getUsername())
                .flatMap(usernameExists -> {
                    if (usernameExists) {
                        return Mono.error(new IllegalArgumentException("Username already exists."));
                    }
                    return userRepository.existsByEmail(signUpDto.getEmail());
                })
                .flatMap(emailExists -> {
                    if (emailExists) {
                        return Mono.error(new IllegalArgumentException("Email already exists."));
                    }
                    User user = new User();
                    user.setUsername(signUpDto.getUsername());
                    user.setEmail(signUpDto.getEmail());
                    user.setPassword(passwordEncoder.encode(signUpDto.getPassword())); // Hash the password
                    return userRepository.save(user)
                            .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED).body(savedUser));
                })
                .onErrorResume(IllegalArgumentException.class, e ->
                        Mono.just(ResponseEntity.badRequest().body(null)) // Handle conflicts in user registration
                );
    }
    @GetMapping("/user/{username}")
    public Mono<ResponseEntity<UserWithRoles>> getUserWithRoles(@PathVariable String username) {
        return userService.findUserWithRolesByUsername(username)
                .map(userWithRoles -> ResponseEntity.ok(userWithRoles))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }
}*/
 /*   @Autowired
    private JwtUtil tokenProvider;*/
/*    @PostMapping("/login")
    public Mono<ResponseEntity<JWTAuthResponse>> authenticateUser(@RequestBody LoginDto loginDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()))
                .flatMap(authentication -> {
                    // Set authentication context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    // Generate token
                    String token = tokenProvider.generateToken(authentication);
                    return Mono.just(ResponseEntity.ok(new JWTAuthResponse(token)));
                })
                .onErrorResume(AuthenticationException.class, ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new JWTAuthResponse("Invalid username or password")))
                );
    }*/

/*
  public Mono<String> authenticateUser(String username, String password) {
        return userRepository.findByUsername(username) // Assuming case-sensitive usernames
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + username)))
                .flatMap(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) { // Check if passwords match
                        String token = jwtUtil.generateToken(user); // Generate a token
                        return Mono.just(token);
                    } else {
                        return Mono.error(new RuntimeException("Invalid credentials")); // Return error if credentials do not match
                    }
                });
    }
 */

     /*   @PostMapping("/login") // Login endpoint
    public Mono<ResponseEntity<JWTAuthResponse>> authenticateUser(@RequestBody LoginDto loginDto) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()))
            .flatMap(authentication -> {
                // Logic to create and return JWT token
                String token = tokenProvider.generateToken(authentication);
                return Mono.just(ResponseEntity.ok(new JWTAuthResponse(token)));
            })
            .onErrorResume(e ->
                Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new JWTAuthResponse("Invalid username or password"))));
    }
  // Check for existing username or email in one reactive chain
        return userRepository.existsByUsername(signUpDto.getUsername())
                .zipWith(userRepository.existsByEmail(signUpDto.getEmail()), (usernameExists, emailExists) -> {
                    if (usernameExists) {
                        throw new IllegalArgumentException("Username already exists.");
                    }
                    if (emailExists) {
                        throw new IllegalArgumentException("Email already exists.");
                    }
                    return true; // Both checks pass
                })
                .flatMap(result -> {
                    // Only create user if checks passed
                    User user = new User();
                    user.setUsername(signUpDto.getUsername());
                    user.setEmail(signUpDto.getEmail());
                    user.setPassword(passwordEncoder.encode(signUpDto.getPassword())); // Hash the password
                    return userRepository.save(user)
                            .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED).body(savedUser));
                })
                .onErrorResume(IllegalArgumentException.class, e ->
                        Mono.just(ResponseEntity.badRequest().body(null)) // Handle potential errors gracefully
                );*/


      /*  return userRepository.existsByUsername(signUpDto.getUsername())
                .flatMap(usernameExists -> {
                    if (usernameExists) {
                        return Mono.error(new IllegalArgumentException("Username already exists."));
                    }
                    return userRepository.existsByEmail(signUpDto.getEmail());
                })
                .flatMap(emailExists -> {
                    if (emailExists) {
                        return Mono.error(new IllegalArgumentException("Email already exists."));
                    }
                    User user = new User();
                    user.setUsername(signUpDto.getUsername());
                    user.setEmail(signUpDto.getEmail());
                    // Store the hashed password
                    user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

                    return userRepository.save(user)
                            .map(savedUser -> ResponseEntity.status(HttpStatus.CREATED).body(savedUser));
                })
                .onErrorResume(IllegalArgumentException.class, e -> {
                    return Mono.just(ResponseEntity.badRequest().body(null));
                });
    }
}*/
/*import com.nas.robospring.Exception.LoginFailedException;
import com.nas.robospring.dto.JWTAuthResponse;
import com.nas.robospring.dto.LoginDto;
import com.nas.robospring.dto.SignUpDto;
import com.nas.robospring.model.User;
import com.nas.robospring.repository.RoleRepository;
import com.nas.robospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        try {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    } catch (
    AuthenticationException ex) {

        throw new LoginFailedException("Invalid username or password"); // Customize the error message
    }
    }

    @PostMapping("/signup")
    public Mono<User> signup(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword())); // Be sure to hash passwords!

        return userRepository.save(user);
    }*/
    /*
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }*/

