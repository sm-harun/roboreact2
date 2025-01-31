package com.nas.robospring.service;

import com.nas.robospring.model.Course;
import com.nas.robospring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Flux<Course> getAllCourses() {
        return courseRepository.findAll(); // Retrieve all courses
    }
    public Mono<Course> getCourseById(Long id) {
        return courseRepository.findById(id); // Make sure this method is defined in your repository
    }
    public Mono<Course> createCourse(Course course) {
        return courseRepository.save(course); // Create a new course
    }

    public Mono<Course> updateCourse(Long id, Course courseDetails) {
        return courseRepository.findById(id)
                .flatMap(course -> {
                    course.setTitle(courseDetails.getTitle());
                    course.setDescription(courseDetails.getDescription());
                    course.setLevel(courseDetails.getLevel());
                    course.setDuration(courseDetails.getDuration());
                    return courseRepository.save(course);
                });
    }

    public Mono<Void> deleteCourse(Long id) {
        return courseRepository.deleteById(id); // Delete course by ID
    }

    public Flux<Course> getCoursesByUserId(Long userId) {
        // This method will interact with the repository to get courses for a specific user
        return courseRepository.findByUserId(userId); // Make sure this method is defined in your repository
    }


}
/*
package com.nas.robospring.service;

import com.nas.robospring.model.Course;
import com.nas.robospring.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CourseService1 {
    private final CourseRepository courseRepository;

    public CourseService1(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }



    public Flux<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Mono<Course> createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Flux<Course> getCoursesByLevel(String level) {
        return courseRepository.findByLevel(level);
    }

    @Transactional(readOnly = true)
    public Flux<Course> searchCourses(String query) {
        return courseRepository.findByTitleContainingIgnoreCase(query);
    }
}


 */