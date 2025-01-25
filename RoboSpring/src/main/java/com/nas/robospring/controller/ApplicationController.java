package com.nas.robospring.controller;

import com.nas.robospring.model.Application;
import com.nas.robospring.service.ApplicationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/join")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public Mono<Application> submitApplication(@RequestBody Application application) {
        return applicationService.submitApplication(application);
    }
}
/*

Using Project Lombok in your Java project can greatly simplify your code by generating boilerplate code
like getters, setters, toString methods, and more at compile time. Here’s a brief look at the various annotations you mentioned
(@Data, @AllArgsConstructor, and @NoArgsConstructor), along with recommendations on when to use them.

Lombok Annotations Explained
@Data

This annotation is a convenience that bundles the functionalities of several other annotations:
@Getter: Generates getters for all fields.
@Setter: Generates setters for all non-final fields.
@ToString: Generates a toString() method that includes all fields.
@EqualsAndHashCode: Generates equals() and hashCode() methods.
@RequiredArgsConstructor: Creates a constructor for final fields.
Use Case: Best suited for simple data holder classes (POJOs) where you'll need access to all those methods.
@AllArgsConstructor

Generates a constructor that takes one parameter for every field in your class.
Use Case: Useful when you want to create instances of the class with fully initialized fields in a single call.
@NoArgsConstructor

Generates a constructor with no parameters.
Use Case: Necessary when you are using frameworks that require a default constructor, such as certain serialization/deserialization processes.
Which is Best?
For Data Classes (DTOs or POJOs): Use @Data when you need to manage data easily. It’s concise and works well for most use cases without
 cluttering your code.
For Constructors: If you need to create instances with all fields initialized:
Use @AllArgsConstructor along with @NoArgsConstructor if you also need a no-arg constructor.
When to Avoid: If your class has a lot of business logic along with data handling, or if you’re using patterns like immutability,
you might want to implement the methods manually for better control.
 */
