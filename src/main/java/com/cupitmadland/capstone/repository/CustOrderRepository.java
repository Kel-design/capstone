package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.CustOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustOrderRepository extends JpaRepository<CustOrder, Long> {
}
