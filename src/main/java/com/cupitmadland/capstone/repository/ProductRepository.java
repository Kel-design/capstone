package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
