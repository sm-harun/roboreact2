package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("orders") // This table will match your database orders table
public class Order {

    @Id
    private Integer id;
    private String userId; // Assuming you'd like to associate each order with a user
    private String paymentStatus; // e.g., "Pending", "Completed", "Cancelled"
    private Double totalAmount; // Total cost of the order
    private LocalDate orderDate; // orderDate of the order

    private List<OrderItem> items; // Assuming you have an OrderItem class to represent each item in the order

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
/*

shippingAddress (for physical products)
orderId (Primary Key)
userId (Foreign Key)
orderDate
totalAmount
paymentStatus
OrderItem
 */
}
