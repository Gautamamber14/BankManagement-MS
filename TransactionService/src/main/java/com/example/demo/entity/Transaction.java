package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

/**
 * Entity representing a transaction.
 */
@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Transaction type is required")
    private String transactionType; // Withdraw or Deposit

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than zero")
    private Double amount;

    private LocalDate transactionDate;

    // Removed accountNumber field if not needed
    private String accountNumber;
}
