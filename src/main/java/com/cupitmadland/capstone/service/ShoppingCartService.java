package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.CartItem;
import com.cupitmadland.capstone.entity.Customer;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public interface ShoppingCartService {

    void addToCart(CartItem cartItem);

    List<CartItem> getCartItemList();

    public void addGuestCartToCustomerCart(Customer customer, HttpSession session);
}
