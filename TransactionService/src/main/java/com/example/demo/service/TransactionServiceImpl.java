package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.exception.TransactionNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.TransactionRepository;

/**
 * Implementation of TransactionService.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Performs a transaction (withdrawal or deposit).
     *
     * @param transaction the transaction details
     * @return the saved transaction
     * @throws ValidationException if the transaction data is invalid
     */
    @Override
    public Transaction performTransaction(Transaction transaction) {
        // Validate transaction data
        if (transaction.getUserId() == null ||
            transaction.getTransactionType() == null || transaction.getTransactionType().isEmpty() ||
            transaction.getAmount() == null || transaction.getAmount() <= 0) {
            throw new ValidationException("Invalid transaction data");
        }

        // Set transaction date
        transaction.setTransactionDate(LocalDate.now());

        // Save transaction
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves transaction statements for a user.
     *
     * @param userId the user ID
     * @return a list of transactions
     * @throws TransactionNotFoundException if no transactions are found for the user
     */
    @Override
    public List<Transaction> getTransactionStatement(Long userId) {
        // Retrieve transactions
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found for user ID: " + userId);
        }

        return transactions;
    }
}
