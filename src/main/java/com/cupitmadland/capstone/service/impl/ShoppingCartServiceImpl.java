package com.cupitmadland.capstone.service.impl;

import com.cupitmadland.capstone.entity.CartItem;
import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final List<CartItem> cartItemList = new ArrayList<>();

    private CustomerRepository customerRepository;

    private CustOrderRepository custOrderRepository;

    private PaymentRepository paymentRepository;

    private ProductRepository productRepository;

    private RoleRepository roleRepository;

    private ShoppingCartRepository shoppingCartRepository;

    private CartItemRepository cartItemRepository;

    private PasswordEncoder passwordEncoder;

    public ShoppingCartServiceImpl(CustomerRepository customerRepository, CustOrderRepository custOrderRepository, PaymentRepository paymentRepository,
                               ProductRepository productRepository, RoleRepository roleRepository, ShoppingCartRepository shoppingCartRepository,
                               CartItemRepository cartItemRepository, PasswordEncoder passwordEncoder){
        super();
        this.customerRepository = customerRepository;
        this.custOrderRepository = custOrderRepository;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addToCart(CartItem cartItem) {
        // Save or update the CartItem in the database
        cartItemRepository.save(cartItem);

    }

    @Override
    public List<CartItem> getCartItemList() {
        // Fetch cart items from the database
        return cartItemRepository.findAll();
    }

    // Method to handle the transition from guest cart to customer cart
    @Override
    public void addGuestCartToCustomerCart(Customer customer, HttpSession session) {

        // Retrieve the guest cart items from the session
        List<CartItem> guestCartItems = (List<CartItem>) session.getAttribute("cartItems");



        if (guestCartItems !=null && !guestCartItems.isEmpty()) {
            // Set the customer for each guest cart item and add them to the customer's cart
            // Save or update the CartItems in the database
            cartItemRepository.saveAll(guestCartItems);
            customer.addCartItems(guestCartItems);
            customerRepository.save(customer);

            // Clear guest cart items from the session
             session.removeAttribute("cartItems");
        }

    }
}
