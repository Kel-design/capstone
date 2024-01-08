package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.CartItem;
import com.cupitmadland.capstone.entity.Customer;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;


/**
 * Service interface for managing shopping cart related operations.
 */
public interface ShoppingCartService {

    /**
     * Adds a cart item to the shopping cart.
     *
     * @param cartItem The cart item to be added.
     */
    void addToCart(CartItem cartItem);

    /**
     * Retrieves a list of cart items in the shopping cart.
     *
     * @return The list of cart items.
     */
    List<CartItem> getCartItemList();

    /**
     * Adds guest cart items to the customer's cart.
     *
     * @param customer The customer for whom the cart items will be added.
     * @param session The HttpSession containing guest cart items.
     */
    public void addGuestCartToCustomerCart(Customer customer, HttpSession session);
}
