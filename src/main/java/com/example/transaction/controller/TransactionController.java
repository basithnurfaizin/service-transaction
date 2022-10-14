package com.example.transaction.controller;

import com.example.transaction.dto.BaseResponse;
import com.example.transaction.dto.TransactionHistoryResponse;
import com.example.transaction.entity.Transaction;
import com.example.transaction.service.TransactionService;
import com.example.transaction.service.TransactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/history")
    public ResponseEntity<BaseResponse<List<TransactionHistoryResponse>>> getHistoryTransaction(@RequestParam UUID accountId,
                                                              @RequestParam(required = false) String startDate,
                                                              @RequestParam(required = false) String endDate) {

        List<TransactionHistoryResponse> transactionResponse = transactionService.getHistoryTransaction(accountId, startDate, endDate).stream()
                .map(transaction -> this.transactionHistoryResponse(transaction, accountId))
                .collect(Collectors.toList());

        BaseResponse<List<TransactionHistoryResponse>> response = new BaseResponse<>("","",transactionResponse,null,null);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    private TransactionHistoryResponse transactionHistoryResponse (Transaction transaction, UUID accountId) {

        TransactionHistoryResponse response = new TransactionHistoryResponse();
        response.setId(transaction.getId());
        response.setTransactionDate(transaction.getTransactionDate());
        if (transaction.getReceiverAccountId().equals(accountId)) {
            response.setAmount(transaction.getAccountedAmount());
        } else {
            response.setAmount(transaction.getAccountedAmount().negate());
        }

        return response;
    }
}
