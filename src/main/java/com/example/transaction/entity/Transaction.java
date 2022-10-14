package com.example.transaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "sender_bank_id")
    private UUID senderBankId;

    @Column(name = "sender_account_id")
    private UUID senderAccountId;

    @Column(name = "receiver_account_id")
    private UUID receiverAccountId;

    @Column(name = "receiver_bank_id")
    private UUID receiverBankId;

    @Column(name = "currency_id")
    private UUID currencyId;

    @Column(name = "entered_amount")
    private BigDecimal enteredAmount;

    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(name = "accounted_amount")
    private BigDecimal accountedAmount;

    private String remark;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}
