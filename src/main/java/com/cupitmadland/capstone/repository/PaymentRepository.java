package com.cupitmadland.capstone.repository;

import com.cupitmadland.capstone.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
