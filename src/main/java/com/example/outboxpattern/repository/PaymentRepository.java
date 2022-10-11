package com.example.outboxpattern.repository;

import com.example.outboxpattern.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
