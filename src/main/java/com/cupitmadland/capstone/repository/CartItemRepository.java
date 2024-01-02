package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
