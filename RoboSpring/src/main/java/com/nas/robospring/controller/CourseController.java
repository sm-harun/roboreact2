package com.nas.robospring.controller;

import com.nas.robospring.model.Course;
import com.nas.robospring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public Flux<Course> getAllCourses() {
        return courseService.getAllCourses(); // Retrieve all courses
    }
    @GetMapping("/{id}")
    public Mono<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id); // Get courses for a specific user
    }
    @PostMapping
    public Mono<ResponseEntity<Course>> createCourse(@RequestBody Course course) {
        return courseService.createCourse(course)
                .map(createdCourse -> ResponseEntity.ok(createdCourse))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Course>> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return courseService.updateCourse(id, courseDetails)
                .map(updatedCourse -> ResponseEntity.ok(updatedCourse))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id)
                .then(Mono.just(ResponseEntity.ok().build()))
                .onErrorReturn(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/user/{userId}")
    public Flux<Course> getCoursesByUserId(@PathVariable Long userId) {
        return courseService.getCoursesByUserId(userId); // Get courses for a specific user
    }
}
