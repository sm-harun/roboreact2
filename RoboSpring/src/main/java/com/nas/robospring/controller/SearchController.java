package com.nas.robospring.controller;

import com.nas.robospring.dto.SearchResult;
import com.nas.robospring.model.CommunityPost;
import com.nas.robospring.model.Course;
import com.nas.robospring.model.Product;
import com.nas.robospring.service.CommunityPostService;
import com.nas.robospring.service.CourseService;
import com.nas.robospring.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;



@RestController
@RequestMapping("/api")
public class SearchController {

    private final ProductService productService;
    private final CourseService courseService;
    private final CommunityPostService communityPostService;

    public SearchController(ProductService productService, CourseService courseService, CommunityPostService communityPostService) {
        this.productService = productService;
        this.courseService = courseService;
        this.communityPostService = communityPostService;
    }
/*
    @GetMapping("/search")
    public Flux<SearchResult> search(@RequestParam String query) {
        Flux<Product> products = productService.searchProducts(query);
        Flux<Course> courses = courseService.searchCourses(query);
        Flux<CommunityPost> posts = communityPostService.searchPosts(query);

        return Flux.merge(
                products.map(product -> new SearchResult("Product", product.getTitle(), product.getProduct_id())),
                courses.map(course -> new SearchResult("Course", course.getTitle(), course.getCourseId())),
                posts.map(post -> new SearchResult("Post", post.getContent(), post.getPostId()))
        );
    }*/
}
