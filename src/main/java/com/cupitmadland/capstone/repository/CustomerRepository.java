package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //custom query to find Customer by email
    Customer findByEmail(String email);
}
