package com.cupitmadland.capstone.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Used to have access to the CartItem line item including product details and quantity
@Entity
@Data
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Integer quantity;




    //Creating a bidirectional @ManyToOne entity relationship for Cart Item to Product using productId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    //Creating a @ManyToOne entity relationship for Cart Item to Shopping cart using shoppingCart_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;

    // Creating a @ManyToOne relationship for CartItem to Customer using customer_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Creating a @ManyToOne relationship for CartItem to Payment using payment_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public void setProductId(Long productId){
        if (product == null){
            product = new Product();
        }
        product.setId(productId);
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }

}
