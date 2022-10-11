package com.example.outboxpattern.repository;

import com.example.outboxpattern.model.PaymentOutBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentOutBoxRepository extends JpaRepository<PaymentOutBox, UUID> {

}
