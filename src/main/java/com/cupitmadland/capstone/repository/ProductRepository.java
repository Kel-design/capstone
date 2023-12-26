package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Custom query method to find a product by size
    Optional<Product> findBySizeAndScent(String size, String scent);
}
