package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Entity class representing a Shopping Cart, holding cart information.
 */
@Entity
@Data
@NoArgsConstructor
public class ShoppingCart {

    /**
     * The unique identifier for the Shopping Cart.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * The total price of the items in the Shopping Cart.
     */
    BigDecimal price;

    /**
     * The total quantity of the items in the Shopping Cart.
     */
    Integer quantity;

    /**
     * The subtotal of the items in the Shopping Cart.
     */
    BigDecimal subtotal;

    /**
     * The list of Products associated with the Shopping Cart.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCart")
    private List<Product> productList;

    /**
     * The list of CartItems associated with the Shopping Cart.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCart")
    private List<CartItem> cartItemList;

}
