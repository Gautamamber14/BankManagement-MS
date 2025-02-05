package com.example.loan.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

/**
 * Entity class representing a loan application.
 */
@Entity
@Data
public class Loan {

    /**
     * Unique identifier for the loan.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    /**
     * The full name of the customer applying for the loan.
     */
    private String customerFullName;

    /**
     * The type of the loan (e.g., personal, home, auto).
     */
    private String loanType;

    /**
     * The amount requested for the loan.
     */
    private double amount;

    /**
     * The ID of the user associated with the loan.
     */
    private Long userId;

    /**
     * The status of the loan application (Pending, Approved, Rejected).
     */
    private String status;

    /**
     * The date when the loan application was submitted.
     */
    private LocalDate applicationDate;

    /**
     * The account number associated with the loan.
     */
    private String accountNumber;
}
