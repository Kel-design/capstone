package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
