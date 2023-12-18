package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
