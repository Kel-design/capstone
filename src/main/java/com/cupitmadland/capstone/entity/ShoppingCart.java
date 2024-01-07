package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

// Used to hold Shopping Cart information.
@Entity
@Data
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal price;

    Integer quantity;

    BigDecimal subtotal;

    //Creating @OneToMany entity relationship for ShoppingCart and Product using shoppingCart_id
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCart")
    private List<Product> productList;

    //Creating @OneToMany entity relationship for ShoppingCart and CartItem using shoppingCart_id
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCart")
    private List<CartItem> cartItemList;

}
