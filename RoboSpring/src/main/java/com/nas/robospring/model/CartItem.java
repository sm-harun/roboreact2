package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("cart_items")
public class CartItem {
    @Id
    private Integer id;
    private String name;
    private Double price;
    private Long userId; // Reference to the user
    private Long productId; // Reference to the product
    private Integer quantity;

    // Getters and setters...
}
