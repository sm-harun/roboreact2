package com.nas.robospring.controller;

import com.nas.robospring.model.CartItem;
import com.nas.robospring.service.CartItemService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartItemService cartItemService;

    public CartController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public Mono<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.addCartItem(cartItem);
    }

    @GetMapping("/user/{userId}")
    public Flux<CartItem> getCartItemsByUserId(@PathVariable Long userId) {
        return cartItemService.getCartItemsByUserId(userId);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCartItem(@PathVariable Long id) {
        return cartItemService.deleteCartItem(id);
    }

    @DeleteMapping("/clear/{userId}")
    public Mono<Void> clearCart(@PathVariable Long userId) {
        return cartItemService.clearCart(userId);
    }
}

/*

import com.nas.robobackend.model.CartItem;
import com.nas.robobackend.service.CartItemService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public Flux<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public Mono<CartItem> getCartItemById(@PathVariable Integer id) {
        return cartItemService.getCartItemById(id);
    }

    @PostMapping
    public Mono<CartItem> createCartItem(@RequestBody CartItem cartItem) {
        return cartItemService.saveCartItem(cartItem);
    }

    @PutMapping("/{id}")
    public Mono<CartItem> updateCartItem(@PathVariable Integer id, @RequestBody CartItem cartItem) {
        cartItem.setId(id);
        return cartItemService.saveCartItem(cartItem);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCartItem(@PathVariable Integer id) {
        return cartItemService.deleteCartItemById(id);
    }
}
*/
