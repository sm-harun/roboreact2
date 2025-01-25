package com.nas.robospring.service;

import com.nas.robospring.model.Order;
import com.nas.robospring.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll(); // Fetch all orders
    }

    public Mono<Order> updateOrderStatus(Integer id, String status) {
        return orderRepository.findById(Long.valueOf(id))
                .flatMap(order -> {
                    order.setPaymentStatus(status); // Update status
                    return orderRepository.save(order); // Save updated order
                });
    }

    public Flux<Order> getOrderHistoryByUserId(Integer userId) {
        // This method will interact with the repository to get the order history for a specific user
        return orderRepository.findByUserId(userId); // Ensure this method is defined in your repository
    }
}
