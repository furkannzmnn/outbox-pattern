package com.example.outboxpattern.scheduler;

import com.example.outboxpattern.repository.PaymentOutBoxRepository;
import com.example.outboxpattern.service.OrderEventSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutBoxRetryService {

    private final PaymentOutBoxRepository paymentOutBoxRepository;
    private final OrderEventSender orderEventSender;

    public void retry() {
        paymentOutBoxRepository.findAll().forEach(paymentOutBox -> {
            orderEventSender.sendOrderCreatedEvent(paymentOutBox.getTransactionId());
            paymentOutBoxRepository.deleteById(paymentOutBox.getId());
        });

    }
}
