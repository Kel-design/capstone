package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.CustOrder;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for managing CustOrder entities in the database.
 * Extends JpaRepository providing basic CRUD operations.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface CustOrderRepository extends JpaRepository<CustOrder, Long> {
}
