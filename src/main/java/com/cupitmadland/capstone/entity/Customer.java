package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a Customer and holding customer information.
 */
@Entity
@Data
@NoArgsConstructor
public class Customer {

    /**
     * The unique identifier for the Customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * The first name of the Customer.
     */
    String firstName;

    /**
     * The middle name of the Customer.
     */
    String middleName;

    /**
     * The last name of the Customer.
     */
    String lastName;

    /**
     * The address of the Customer.
     */
    String address;

    /**
     * The city of the Customer.
     */
    String city;

    /**
     * The state of the Customer.
     */
    String state;

    /**
     * The ZIP Code of the Customer.
     */
    String zipCode;

    /**
     * The phone number of the Customer.
     */
    String phone;

    /**
     * The email address of the Customer.
     */
    String email;

    /**
     * The contact us message associated with the Customer (limited to 1000 characters).
     */
    @Column(name = "message", length = 1000)
    String message;

    /**
     * The username associated with the Customer.
     */
    @Column(nullable = true)
    String username;

    /**
     * The password associated with the Customer.
     */
    @Column(nullable = true, unique = true)
    String password;

    /**
     * The Shopping Cart associated with the Customer.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCartId")
    private ShoppingCart shoppingCart;


    /**
     * The list of Payments associated with the Customer.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Payment> paymentList;

    /**
     * The list of Roles associated with the Customer.
     */
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_roles", joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    public List<Role> roles = new ArrayList<>();

    /**
     * The list of CartItems associated with the Customer.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<CartItem> cartItems = new ArrayList<>();

    /**
     * Adds a list of CartItems to the Customer's cartItems list and sets the customer reference for each CartItem.
     * @param cartItems The list of CartItems to be associated with the Customer.
     */
    public void addCartItems(List<CartItem> cartItems) {
        cartItems.forEach(cartItem -> {
            cartItem.setCustomer(this);
            this.cartItems.add(cartItem);
        });

        }
    }

