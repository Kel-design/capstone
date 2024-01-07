package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Entity to hold Customer information
@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String middleName;

    String lastName;

    String address;

    String city;

    String state;

    String zipCode;

    String phone;

    String email;

    @Column(name = "message", length = 1000)
    String message;

    @Column(nullable = true)
    String username;

    @Column(nullable = true, unique = true)
    String password;

    //Creating a @OneToOne entity relationship for Customer to Shopping Cart using shoppingCartId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCartId")
    private ShoppingCart shoppingCart;

    //Creating a @OneToMany entity relationship for Customer to CustOrder using customer_id
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    //private List<CustOrder> custOrderList;

    //Creating a @OneToMany entity relationship for Customer to Payment using customer_id
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Payment> paymentList;

    //Creating a @ManyToMany entity relationship for Customer to Role using customer_id and role_id
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_roles", joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    public List<Role> roles = new ArrayList<>();

    // Creating a bidirectional @OneToMany relationship for Customer to CartItem
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<CartItem> cartItems = new ArrayList<>();

    // New method to add a CartItem to the list and set the customer reference
    public void addCartItems(List<CartItem> cartItems) {
        cartItems.forEach(cartItem -> {
            cartItem.setCustomer(this);
            this.cartItems.add(cartItem);
        });

        }
    }

