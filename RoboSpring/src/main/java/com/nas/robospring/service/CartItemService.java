package com.nas.robospring.service;

import com.nas.robospring.model.CartItem;
import com.nas.robospring.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public Mono<CartItem> addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public Flux<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public Mono<Void> deleteCartItem(Long id) {
        return cartItemRepository.deleteById(id);
    }

    public Mono<Void> clearCart(Long userId) {
        return cartItemRepository.deleteAll(cartItemRepository.findByUserId(userId)); // Assuming you will find and delete all cart items for the user
    }
}
/*

import com.nas.robospring.model.CartItem;
import com.nas.robospring.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public Flux<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Mono<CartItem> getCartItemById(Integer id) {
        return cartItemRepository.findById(id);
    }

    public Mono<CartItem> saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public Mono<Void> deleteCartItemById(Integer id) {
        return cartItemRepository.deleteById(id);
    }
}
*/
