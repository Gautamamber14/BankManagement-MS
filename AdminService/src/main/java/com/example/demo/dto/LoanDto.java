package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the details of a loan application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

    /**
     * Unique identifier for the loan.
     */
    private Long loanId;

    /**
     * Full name of the customer applying for the loan.
     */
    private String customerFullName;

    /**
     * Type of the loan (e.g., "Personal", "Mortgage", "Auto").
     */
    private String loanType;

    /**
     * Amount requested for the loan.
     */
    private double amount;

    /**
     * Unique identifier of the user associated with the loan.
     */
    private Long userId;

    /**
     * Current status of the loan application.
     * Possible values: "Pending", "Approved", "Rejected".
     */
    private String status; // Pending, Approved, Rejected

    /**
     * Date when the loan application was submitted.
     */
    private LocalDate applicationDate;
}
