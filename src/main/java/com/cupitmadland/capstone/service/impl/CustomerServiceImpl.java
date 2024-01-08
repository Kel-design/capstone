package com.cupitmadland.capstone.service.impl;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.entity.Role;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of teh CustomerService interface.
 * Used to save customer information, find customers by email, and manage customer roles.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustOrderRepository custOrderRepository;

    private PaymentRepository paymentRepository;

    private ProductRepository productRepository;

    private RoleRepository roleRepository;

    private ShoppingCartRepository shoppingCartRepository;

    private CartItemRepository cartItemRepository;

    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustOrderRepository custOrderRepository, PaymentRepository paymentRepository,
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

    /**
     * Saves customer information, encodes the password, assigns a default role, and sets a default username if necessary.
     * @param customer The customer to be saved.
     */
    @Override
    public void saveCustomer(Customer customer) {

        customer.setEmail(customer.getEmail());

        // Only encode the password if it is not null or empty
        if (customer.getPassword() != null && !customer.getPassword().isEmpty()) {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        }

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        customer.setRoles(Arrays.asList(role));

        // Set a default username if it is null or empty
        if (customer.getUsername() == null || customer.getUsername().isEmpty()) {
            customer.setUsername("default_username");
        }
        customerRepository.save(customer);
    }

    /**
     * Find a customer by email.
     *
     * @param email The email of the customer to be found.
     * @return The customer with the specified email.
     */
    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    /**
     * Finds all customers.
     *
     * @return A list of all customers.
     */
    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    /**
     * Checks if the role "ROLE-USER" exists; creates and returns if not found.
     *
     * @return The "ROLE_USER" role.
     */
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
