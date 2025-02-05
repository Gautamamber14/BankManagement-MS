package com.example.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loan.entity.Loan;
import com.example.loan.exception.InvalidLoanAmountException;
import com.example.loan.exception.LoanNotFoundException;
import com.example.loan.repository.LoanRepository;

/**
 * Implementation of the LoanService interface.
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    /**
     * Applies for a new loan.
     *
     * @param loan the loan to apply for
     * @return the saved loan
     * @throws InvalidLoanAmountException if the loan amount exceeds the allowed limit
     */
    @Override
    public Loan applyForLoan(Loan loan) {
        if (loan.getAmount() > 500000) {
            throw new InvalidLoanAmountException("Loan amount cannot exceed 5 lakh");
        }
        return loanRepository.save(loan);
    }

    /**
     * Retrieves a loan by its ID.
     *
     * @param loanId the ID of the loan
     * @return the loan
     * @throws LoanNotFoundException if the loan is not found
     */
    @Override
    public Loan getLoan(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + loanId));
    }

    /**
     * Retrieves all loans for a specific user.
     *
     * @param userId the ID of the user
     * @return list of loans
     */
    @Override
    public List<Loan> getAllLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    /**
     * Updates the status of a loan.
     *
     * @param loanId the ID of the loan
     * @param status the new status
     * @return the updated loan
     * @throws LoanNotFoundException if the loan is not found
     */
    @Override
    public Loan updateLoanStatus(Long loanId, String status) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + loanId));
        loan.setStatus(status);
        return loanRepository.save(loan);
    }

    /**
     * Retrieves all loans.
     *
     * @return list of all loans
     */
    @Override
    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }
}
