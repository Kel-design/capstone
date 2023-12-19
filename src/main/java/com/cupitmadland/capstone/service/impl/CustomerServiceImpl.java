package com.cupitmadland.capstone.service.impl;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustOrderRepository custOrderRepository;

    private PaymentRepository paymentRepository;

    private ProductRepository productRepository;

    private ShoppingCartRepository shoppingCartRepository;

    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustOrderRepository custOrderRepository, PaymentRepository paymentRepository,
                               ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository,
                               PasswordEncoder passwordEncoder){
        super();
        this.customerRepository = customerRepository;
        this.custOrderRepository = custOrderRepository;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveCustomer(Customer customer) {
        //need to write code here!
    }
}
