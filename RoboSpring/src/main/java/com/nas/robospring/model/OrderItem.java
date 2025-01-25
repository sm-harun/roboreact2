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
@Table("items")
public class OrderItem {

    @Id
    private Integer id; // Unique identifier for the order item
    private String name; // Name of the order item

    private Integer orderId; // ID of the order ordered
    private Integer productId; // ID of the product ordered
    private Double price; // Price per item
    private Integer quantity; // Quantity ordered

    /*
    orderItemId (Primary Key)
orderId (Foreign Key)
productId (Foreign Key)
quantity
     */
}
