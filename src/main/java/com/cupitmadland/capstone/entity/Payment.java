package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Entity class representing a Payment, holding customer payment information.
 */
@Entity
@Data
@NoArgsConstructor
public class Payment {

    /**
     * The unique identifier for the Payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * The timestamp of the payment.
     */
    Timestamp paymentDate;

    /**
     * The total amount of the payment.
     */
    BigDecimal totalAmount;

    /**
     * The credit card number used for the payment.
     */
    String creditCardNum;

    /**
     * The expiration date of the credit card.
     */
    String expDate;

    /**
     * The security code of the credit card.
     */
    String securityCode;

    /**
     * The name on the credit card.
     */
    String nameOnCard;

    /**
     * The Customer associated with the Payment.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * The list of CartItems associated with the Payment.
     */
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;


}
