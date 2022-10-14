package com.example.transaction.service;

import com.example.transaction.entity.Transaction;
import com.example.transaction.repository.TransactionRepository;
import com.example.transaction.repository.spec.TransactionSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionSpecification transactionSpecification;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionSpecification transactionSpecification) {
        this.transactionRepository = transactionRepository;
        this.transactionSpecification = transactionSpecification;
    }

    @Override
    public List<Transaction> getHistoryTransaction(UUID accountId, String startDate, String endDate) {

        Specification<Transaction> specification = transactionSpecification.getSpecification(accountId, startDate, endDate);

        return transactionRepository.findAll(specification);
    }
}
