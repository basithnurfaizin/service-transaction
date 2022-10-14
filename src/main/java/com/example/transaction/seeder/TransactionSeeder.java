package com.example.transaction.seeder;

import com.example.transaction.entity.Transaction;
import com.example.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionSeeder {

    private final TransactionRepository transactionRepository;

    public TransactionSeeder(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @PostConstruct
    public void generateData() {

        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {

            UUID senderId = UUID.randomUUID();
            UUID receiverId = UUID.randomUUID();
            UUID bankId = UUID.randomUUID();
            UUID currencyId = UUID.randomUUID();

            Transaction transactionSender = Transaction.builder()
                    .transactionDate(LocalDateTime.now())
                    .receiverBankId(bankId)
                    .senderBankId(bankId)
                    .senderAccountId(senderId)
                    .receiverAccountId(receiverId)
                    .currencyId(currencyId)
                    .exchangeRate(BigDecimal.valueOf(15000.0))
                    .accountedAmount(BigDecimal.valueOf(1000000.0))
                    .enteredAmount(BigDecimal.valueOf(1000000.0))
                    .build();

            Transaction transactionReceiver = Transaction.builder()
                    .transactionDate(LocalDateTime.now())
                    .receiverBankId(bankId)
                    .senderBankId(bankId)
                    .senderAccountId(receiverId)
                    .receiverAccountId(senderId)
                    .currencyId(currencyId)
                    .exchangeRate(BigDecimal.valueOf(15000.0))
                    .accountedAmount(BigDecimal.valueOf(1000000.0))
                    .enteredAmount(BigDecimal.valueOf(1000000.0))
                    .build();

            transactionRepository.save(transactionSender);
            transactionRepository.save(transactionReceiver);

        }
    }
}
