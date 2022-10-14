package com.example.transaction.service;

import com.example.transaction.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    List<Transaction> getHistoryTransaction(UUID accountId, String startDate, String endDate);
}
