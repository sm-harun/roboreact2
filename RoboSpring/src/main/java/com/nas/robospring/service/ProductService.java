package com.nas.robospring.service;

import com.nas.robospring.model.Product;
import com.nas.robospring.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }

    public Mono<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setImage(productDetails.getImage());
                    product.setStock(productDetails.getStock());
                    product.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(product);
                });
    }

}
/*@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<Product> getAllProducts() {
        return productRepository.findAll(); // Retrieve all products
    }

    public Mono<Product> createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product); // Create a new product
    }

    public Mono<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setImage(productDetails.getImage());
                    product.setStock(productDetails.getStock());
                    product.setUpdatedAt(LocalDateTime.now());
                    return productRepository.save(product);
                });
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.deleteById(id); // Delete product by ID
    }
}*/
/*
 public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
 */
