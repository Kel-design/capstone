package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a CartItem, which is a line item in a shopping cart, including product details and quantity.
 */
@Entity
@Data
@NoArgsConstructor
public class CartItem {

    /**
     * The unique identifier for the CartItem.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * The quantity of the product in the CartItem.
     */
    private Integer quantity;


    /**
     * The Product associated with the CartItem
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    /**
     * The Shopping Cart associated with the CartItem.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;

    /**
     * The Customer associated with the CartItem.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * The Payment associated with the CartItem.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    /**
     * Sets the product ID for the CartItem. If the product is null, a new Product instance is created.
     *
     * @param productId The ID of the product to be associated with the CartItem.
     */
    public void setProductId(Long productId){
        if (product == null){
            product = new Product();
        }
        product.setId(productId);
    }

    /**
     * Sets the product for the CartItem.
     *
     * @param product The Product to be associated with the CartItem.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Sets the quantity of the product in the CartItem.
     *
     * @param quantity The quantity of the product.
     */
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

}
