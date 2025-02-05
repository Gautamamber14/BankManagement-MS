package com.example.loan.service;

import java.util.List;

import com.example.loan.entity.Loan;

/**
 * Service interface for loan operations.
 */
public interface LoanService {

    /**
     * Applies for a new loan.
     *
     * @param loan the loan details
     * @return the saved loan
     */
    Loan applyForLoan(Loan loan);

    /**
     * Retrieves a loan by its ID.
     *
     * @param loanId the ID of the loan
     * @return the loan
     */
    Loan getLoan(Long loanId);

    /**
     * Retrieves all loans for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of loans
     */
    List<Loan> getAllLoansByUserId(Long userId);

    /**
     * Retrieves all loans.
     *
     * @return a list of all loans
     */
    List<Loan> getAllLoan();

    /**
     * Updates the status of a loan.
     *
     * @param loanId the ID of the loan
     * @param status the new status
     * @return the updated loan
     */
    Loan updateLoanStatus(Long loanId, String status);
}
