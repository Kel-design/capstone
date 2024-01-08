package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Customer entities in the database.
 * Extends JpaRepository providing basic CRUD operations.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    /**
     * Custom query to find a Customer by email.
     *
     * @param email the email address of the Customer to be retrieved
     * @return the Customer entity if found, or null if not found
     */
    Customer findByEmail(String email);
}
