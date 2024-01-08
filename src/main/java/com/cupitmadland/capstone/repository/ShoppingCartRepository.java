package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Repository interface for managing ShoppingCart entities in the database.
 *  Extends JpaRepository providing basic CRUD operations.
 *
 *  @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
