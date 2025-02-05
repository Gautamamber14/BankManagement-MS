package com.example.loan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.loan.entity.Loan;
import com.example.loan.service.LoanService;

import lombok.AllArgsConstructor;

/**
 * Controller for managing loan operations.
 */
@RestController
@RequestMapping("/loans")
@AllArgsConstructor
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * Applies for a new loan.
     *
     * @param loan the loan details
     * @return the applied loan
     */
    @PostMapping("/apply") // Endpoint: http://localhost:8082/loans/apply
    public Loan applyForLoan(@RequestBody Loan loan) {
        return loanService.applyForLoan(loan);
    }

    /**
     * Retrieves a loan by its ID.
     *
     * @param loanId the ID of the loan
     * @return the loan
     */
    @GetMapping("/{loanId}") // Endpoint: http://localhost:8082/loans/{loanId}
    public Loan getLoanByLoanId(@PathVariable Long loanId) {
        return loanService.getLoan(loanId);
    }

    /**
     * Retrieves all loans for a specific user.
     *
     * @param userId the ID of the user
     * @return list of loans for the user
     */
    @GetMapping("/getAllLoansByUserId/{userId}") // Endpoint: http://localhost:8082/loans/getAllLoansByUserId/{userId}
    public List<Loan> getAllLoansByUserId(@PathVariable Long userId) {
        return loanService.getAllLoansByUserId(userId);
    }

    /**
     * Retrieves all loans.
     *
     * @return list of all loans
     */
    @GetMapping("/getAllLoan") // Endpoint: http://localhost:8082/loans/getAllLoan
    public List<Loan> getAllLoan() {
        return loanService.getAllLoan();
    }

    /**
     * Updates the status of a loan.
     *
     * @param loanId the ID of the loan
     * @param status the new status
     * @return the updated loan
     */
    @PutMapping("/update/{loanId}/{status}") // Endpoint: http://localhost:8082/loans/update/{loanId}/{status}
    public Loan updateLoanStatus(@PathVariable Long loanId, @PathVariable String status) {
        return loanService.updateLoanStatus(loanId, status);
    }
}
