package com.example.transaction.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionHistoryResponse {

    private UUID id;

    private UUID senderAccountId;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

}
