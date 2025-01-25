package com.nas.robospring.controller;

import com.nas.robospring.dto.OrderStatusUpdateRequest;
import com.nas.robospring.model.Order;
import com.nas.robospring.service.OrderService;
import com.nas.robospring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService; // Inject UserService to access user order functions

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

  /*  @GetMapping("/user/{userId}") // Endpoint to get orders by user ID
    public Flux<Order> getUserOrders(@PathVariable Integer userId) {
        return userService.fetchUserOrders(userId); // Fetch user orders using user ID
    }*/
    @GetMapping
    public Flux<Order> getAllOrders() {
        return orderService.getAllOrders(); // Fetch all orders from the service
    }

    @PutMapping("/{id}") // Update order method
    public Mono<ResponseEntity<Order>> updateOrderStatus(@PathVariable Integer id, @RequestBody OrderStatusUpdateRequest request) {
        return orderService.updateOrderStatus(id, request.getStatus()) // Assume you have a service method
                .map(updatedOrder -> ResponseEntity.ok(updatedOrder)) // Return updated order
                .defaultIfEmpty(ResponseEntity.notFound().build()); // Handle not found
    }
}
