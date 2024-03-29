package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Initially created to handle the customer order, but later used CartItem for further break down of CartItem line items.
 * Saving to possibly use in future improvements when separating shopping cart and checkout process.
  */

@Entity
@Data
@NoArgsConstructor
public class CustOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer quantity;

    BigDecimal totalAmount;

    //Creating an @OneToOne entity relationship for CustOrder to Payment using paymentId
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "paymentId")
    //private Payment payment;

    //Creating a @ManyToOne entity relationship for CustOrder to Customer using customer_id
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "customer_id")
    //private Customer customer;

    //Creating a @OneToMany entity relationship for CustOrder to Product using custOrder_id
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "custOrder")
    //private List<Product> productList;

    // Creating a @OneToMany entity relationship for CustOrder to CartItems using custOrder_id
    //@OneToMany(fetch= FetchType.LAZY, mappedBy = "custOrder")
    //private List<CartItem> cartItems;



}
