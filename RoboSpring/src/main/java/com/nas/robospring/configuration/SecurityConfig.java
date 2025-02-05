package com.nas.robospring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter; // Your JWT filter should be injected here
    @Autowired
    private CustomLoggingFilter customLoggingFilter; // Your JWT filter should be injected here
    @Autowired
    private LoggingFilter loggingFilter; // Your JWT filter should be injected here
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint; // Use your custom entry point

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 /*   @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/auth/signup","/api/auth/login").permitAll() // Allow                        .pathMatchers(HttpMethod.POST, "/api/competitions").authenticated()
                        .anyExchange().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }*/

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/auth/signup", "/api/auth/login").permitAll()
                        .anyExchange().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                )
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

  /*  @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http
           .csrf(csrf -> csrf.disable())  // Disable CSRF for development (not recommended for production)
        .authorizeExchange()
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
           .pathMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/auth/signup","/api/auth/login").permitAll() // Allow auth requests
//              .pathMatchers(HttpMethod.OPTIONS, "/api/competitions/**").permitAll() // Allow DELETE requests for competitions
                .anyExchange().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());// Register your custom entry point

        return http.build();
//        return http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }*/

   @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // Allow credentials
        configuration.addAllowedOrigin("http://localhost:8081"); // Allow React frontend origin
        configuration.addAllowedHeader("*"); // Allow all headers
        configuration.addAllowedMethod("*"); // Allow all HTTP methods

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration for all routes
        return source;
    }


}
     /*  http.addFilterBefore(jwtAuthenticationFilter, SecurityWebFilterChain.class);
        // Register your custom logging filter
        http.addFilterBefore(customLoggingFilter, JwtAuthenticationFilter.class);*/
          /*    .and()
                .addFilterBefore(loggingFilter, JwtAuthenticationFilter.class); // add your logging filter before others*/     /* .exceptionHandling()
                .authenticationEntryPoint((ServerAuthenticationEntryPoint) jwtAuthenticationFilter)
                .and()*/

   /*   .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()*/
   /*     .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()*/
/*

CORS Issues
Ensure the backend allows requests from the frontend (React Native app).
Example CORS configuration:
@Bean
public CorsWebFilter corsWebFilter() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.addAllowedOrigin("*");
    corsConfig.addAllowedMethod("*");
    corsConfig.addAllowedHeader("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);

    return new CorsWebFilter(source);
The error message you are encountering, Parameter 1 of constructor in com.nas.restaurantapp.controller.UserController required a bean of type 'org.springframework.security.authentication.AuthenticationManager' that could not be found, indicates that Spring is unable to find a bean definition for the AuthenticationManager that your UserController needs.

To resolve this, you will need to ensure that your security configuration correctly defines the AuthenticationManager bean for your Spring Boot application. Below are the steps to set it up:

Step 1: Configure Authentication Manager
In your Spring Security configuration class, you can define the AuthenticationManager bean. Here's how to do it:

java
Copy
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf().disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .pathMatchers("/api/login", "/api/register").permitAll()
            .pathMatchers("/api/users/me").authenticated()
            .anyExchange().authenticated();

        return http.build();
    }

    // Create AuthenticationManager bean
    @Bean
    public AuthenticationManager authManager() throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder();
        // Configure UsersDetailsService
        authenticationManagerBuilder.userDetailsService(userDetailsService()); // Set your user details service
        return authenticationManagerBuilder.build();
    }

    // Define your UserDetailsService bean
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(); // Use your implementation, e.g., from the database
    }
}
Step 2: UserDetailsService Implementation
If you're using an in-memory user store for testing or need to connect to a database, make sure your UserService properly implements user fetching logic.

java
Copy
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository; // Inject your repository

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .block();  // Blocking for demonstration purposes; adapt for reactive context
    }

    // Other user-related methods
}


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf().disable() // Disable CSRF (or configure CSRF if needed)
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow all OPTIONS requests for CORS
            .pathMatchers("/api/**").permitAll() // Allow access to all API endpoints
            .anyExchange().authenticated();

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // Allow credentials
        configuration.addAllowedOrigin("http://localhost:8081"); // Allow React frontend origin
        configuration.addAllowedHeader("*"); // Allow all headers
        configuration.addAllowedMethod("*"); // Allow all HTTP methods

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration for all routes
        return source;
    }
}

Explanation of Key Points
CORS Configuration:
By allowing requests from http://localhost:8081 in your Spring Boot application,
 you enabled your React frontend to communicate with your backend without being blocked by CORS policy.
 This is crucial when the frontend and backend are served from different origins during development.

Handling Network Requests:
The warning regarding slow network detection is common when accessing resources that may take some time to load (like images or fonts).
 As indicated, the fallback font was used while waiting for the font file to load. You may check your font loading configurations
  to ensure everything is properly set up.

Image Loading Issues:
The error net::ERR_NAME_NOT_RESOLVED when trying to load https://via.placeholder.com/150 suggests that there was
a problem resolving that specific URL at the time of loading. This could be due to a temporary network issue,
DNS resolution problem, or even accessibility of that specific external service. You could try loading a different URL or
 check your network connection if it happens frequently.
 */
