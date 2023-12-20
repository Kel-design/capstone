package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.Customer;

import java.util.List;

public interface CustomerService {

    void saveCustomer(Customer customer);

    Customer findCustomerByEmail(String email);

    List<Customer> findAllCustomer();
}
