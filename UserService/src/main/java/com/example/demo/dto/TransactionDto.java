package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Transaction information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Long transactionId;

    /**
     * The ID of the user performing the transaction.
     */
    private Long userId;

    /**
     * The type of transaction (e.g., Withdraw, Deposit).
     */
    private String transactionType;

    /**
     * The amount involved in the transaction.
     */
    private double amount;

    /**
     * The date when the transaction occurred.
     */
    private LocalDate transactionDate;

    /**
     * The account number associated with the transaction.
     */
    private String accountNumber;
}
