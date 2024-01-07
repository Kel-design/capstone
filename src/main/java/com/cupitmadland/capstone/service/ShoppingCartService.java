package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.CartItem;
import com.cupitmadland.capstone.entity.Customer;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
// Used to add cart items to cart, get a cart item list and transition guest cart to customer cart
public interface ShoppingCartService {

    void addToCart(CartItem cartItem);

    List<CartItem> getCartItemList();

    public void addGuestCartToCustomerCart(Customer customer, HttpSession session);
}
