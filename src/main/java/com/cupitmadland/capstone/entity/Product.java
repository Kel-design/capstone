package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    private String scent;

    private String size;

    BigDecimal price;

    Integer stockQuantity;

    //Creating a @ManyToOne entity relationship for Product and CustOrder using custOrder_id
   // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "custOrder_id")
    //private CustOrder custOrder;

    //Creating a @ManyToOne entity relationship for Product and ShoppingCart using shoppingCart_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;


}
