package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Payment entities in the database.
 * Extends JpaRepository providing basic CRUD operations.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
