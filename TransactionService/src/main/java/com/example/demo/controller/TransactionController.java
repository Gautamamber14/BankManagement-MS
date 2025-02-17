package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;

import jakarta.validation.Valid;

/**
 * Controller for managing transactions.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Performs a transaction (withdrawal or deposit).
     *
     * @param transaction the transaction details
     * @return the performed transaction
     */
    @PostMapping("/perform")
    public Transaction performTransaction(@Valid @RequestBody Transaction transaction) {
        return transactionService.performTransaction(transaction);
    }

    /**
     * Retrieves transaction statements for a user.
     *
     * @param userId the user ID
     * @return a list of transactions
     */
    @GetMapping("/statement/{userId}")
    public List<Transaction> getTransactionStatement(@Valid @PathVariable Long userId) {
        return transactionService.getTransactionStatement(userId);
    }
}
