package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing CartItem entities in the database.
 * Extends JpaRepository providing basic CRUD operations.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
