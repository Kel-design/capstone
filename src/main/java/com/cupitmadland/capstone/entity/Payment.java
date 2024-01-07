package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

// Entity to hold Customer payment information.
@Entity
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Timestamp paymentDate;

    BigDecimal totalAmount;

    String creditCardNum;

    String expDate;

    String securityCode;

    String nameOnCard;

    //Creating a @ManyToOne entity relationship for Payment to Customer using customer_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Creating a @OneToMany entity relation for Payment to CartItem
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;


}
