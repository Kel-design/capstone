package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * Entity class representing a Product, holding product information.
 */
@Entity
@Data
@NoArgsConstructor
public class Product {

    /**
     * The unique identifier for the Product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * The name of the Product.
     */
    String name;

    /**
     * The description of the Product.
     */
    String description;

    /**
     * The scent of the Product.
     */
    private String scent;

    /**
     * The size of the Product.
     */
    private String size;

    /**
     * The price of the Product.
     */
    BigDecimal price;

    /**
     * The stock quantity of the Product.
     */
    Integer stockQuantity;

    /**
     * The ShoppingCart associated with the Product.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;

}
