package com.nas.robospring.configuration;
import com.nas.robospring.dto.JWTAuthResponse;
import com.nas.robospring.dto.LoginDto;
import com.nas.robospring.dto.SignUpDto;
import com.nas.robospring.model.User;
import com.nas.robospring.repository.RoleRepository;
import com.nas.robospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

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

}
     /*   // Check for existing username or email in one reactive chain
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

