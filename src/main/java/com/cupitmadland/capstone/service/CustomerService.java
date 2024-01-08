package com.cupitmadland.capstone.service;

import com.cupitmadland.capstone.entity.Customer;

import java.util.List;

/**
 * Service interface for managing customer-related operations.
 */
public interface CustomerService {

    /**
     * Saves a customer to the database.
     *
     * @param customer The customer to be saved.
     */
    void saveCustomer(Customer customer);

    /**
     * Finds a customer by email.
     *
     * @param email The email of the customer to be found.
     * @return The found customer or null if not found.
     */
    Customer findCustomerByEmail(String email);

    /**
     * Retrieves a list of all customers.
     *
     * @return A list of all customers.
     */
    List<Customer> findAllCustomer();
}
