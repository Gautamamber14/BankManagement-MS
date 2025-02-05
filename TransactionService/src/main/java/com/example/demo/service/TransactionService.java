package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Transaction;

/**
 * Service interface for transaction operations.
 */
public interface TransactionService {
    Transaction performTransaction(Transaction transaction);
    List<Transaction> getTransactionStatement(Long userId);
}
