package com.nas.robospring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("product_details")
public class ProductDetails {
    @Id
    private Long detail_id; // Unique identifier for the detail entry
    private Long product_id; // Foreign key to the Product table
    private String specifications; // Detailed specifications
    private String reviews; // User reviews
    private String additionalInfo; // Any other relevant info
}
