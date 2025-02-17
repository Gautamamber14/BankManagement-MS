package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "Transaction type is required")
    @Size(min = 5, max = 30, message = "Transaction type must be between 5 and 30 characters")
    private String transactionType;

    /**
     * The amount involved in the transaction.
     */
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than zero")
    private BigDecimal amount;

    /**
     * The date when the transaction occurred.
     */
  
    private LocalDate transactionDate;

    /**
     * The account number associated with the transaction.
     */
   
    private String accountNumber;
    
    /**
     * The customername associated with the transaction.
  */
    private String customerFullName;
}
