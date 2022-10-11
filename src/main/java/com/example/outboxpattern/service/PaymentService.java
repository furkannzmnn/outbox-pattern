package com.example.outboxpattern.service;

import com.example.outboxpattern.model.Payment;
import com.example.outboxpattern.model.PaymentOutBox;
import com.example.outboxpattern.repository.PaymentOutBoxRepository;
import com.example.outboxpattern.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentOperations {

    private final PaymentRepository paymentRepository;
    private final OrderEventSender orderEventSender;
    private final PaymentOutBoxRepository paymentOutBoxRepository;

    @Transactional
    @Override
    public void createPayment(String transactionId) {

        final UUID outboxId = UUID.randomUUID();

        // save payment to database
        paymentRepository.save(Payment.builder()
                .transactionId(transactionId)
                .amount(BigDecimal.ONE)
                .build());

        paymentOutBoxRepository.save(PaymentOutBox.builder()
                .transactionId(transactionId)
                .id(outboxId)
                .amount(String.valueOf(BigDecimal.ONE))
                .build());

        // send order created event to kafka
        orderEventSender.sendOrderCreatedEvent("order created event");


        paymentOutBoxRepository.deleteById(outboxId);
    }
}
