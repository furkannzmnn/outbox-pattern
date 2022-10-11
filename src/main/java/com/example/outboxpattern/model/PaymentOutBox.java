package com.example.outboxpattern.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = "payments_outbox")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PaymentOutBox {
    @Id
    private UUID id;
    private String transactionId;
    private String amount;

}
