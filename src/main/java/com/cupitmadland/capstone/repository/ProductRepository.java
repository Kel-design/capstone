package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Repository interface for managing Produce entities in the database.
 * Extends JpaRepository providing basic CRUD operations.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    //Custom query method to find a product by size

    /**
     * Custom query method to find a product by size and scent.
     * Optional (wrapper around a Product object) used in case either a size or scent is absent.
     *
     * @param size the size of the product to be retrieved
     * @param scent the scent of the product to be retrieved
     * @return an Optional containing the Product entity if found,or an empty Optional if not found.
     */
    Optional<Product> findBySizeAndScent(String size, String scent);
}
