package com.cupitmadland.capstone.service.impl;

import com.cupitmadland.capstone.entity.Customer;
import com.cupitmadland.capstone.entity.Role;
import com.cupitmadland.capstone.repository.*;
import com.cupitmadland.capstone.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustOrderRepository custOrderRepository;

    private PaymentRepository paymentRepository;

    private ProductRepository productRepository;

    private RoleRepository roleRepository;

    private ShoppingCartRepository shoppingCartRepository;

    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustOrderRepository custOrderRepository, PaymentRepository paymentRepository,
                               ProductRepository productRepository, RoleRepository roleRepository, ShoppingCartRepository shoppingCartRepository,
                               PasswordEncoder passwordEncoder){
        super();
        this.customerRepository = customerRepository;
        this.custOrderRepository = custOrderRepository;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveCustomer(Customer customer) {
        //need to write code here!

        customer.setUsername(customer.getUsername());
        customer.setEmail(customer.getEmail());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if (role == null) {
            role = checkRoleExist();
        }
        customer.setRoles(Arrays.asList(role));
        customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}
