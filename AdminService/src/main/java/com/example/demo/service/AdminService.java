package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LoanDto;

/**
 * **AdminService**
 *
 * Interface defining administrative operations for managing loans.
 */
public interface AdminService {

    /**
     * Retrieves a list of all loans.
     *
     * @return a list of {@code LoanDto} objects representing all loans.
     */
    List<LoanDto> getAllLoans();

    /**
     * Retrieves a loan by its unique identifier.
     *
     * @param loanId the unique ID of the loan to retrieve.
     * @return a {@code LoanDto} representing the loan.
     * @throws LoanNotFoundException if no loan is found with the given ID.
     */
    LoanDto getLoanById(Long loanId);

    /**
     * Updates the status of a specified loan.
     *
     * @param loanId the unique ID of the loan to update.
     * @param status the new status to set for the loan.
     * @return the updated {@code LoanDto} object.
     * @throws LoanNotFoundException if no loan is found with the given ID.
     */
    LoanDto updateLoanStatus(Long loanId, String status);
}
