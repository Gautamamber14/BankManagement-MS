package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.client.LoanServiceClient;
import com.example.demo.dto.LoanDto;
import com.example.demo.exception.LoanNotFoundException;

/**
 * Service implementation for administrative operations on loans.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private LoanServiceClient loanServiceClient;

    /**
     * Retrieves all loans from the loan service.
     *
     * @return a list of LoanDto representing all loans.
     */
    @Override
    public List<LoanDto> getAllLoans() {
        return loanServiceClient.getAllLoan();
    }

    /**
     * Retrieves a loan by its ID from the loan service.
     *
     * @param loanId the unique identifier of the loan.
     * @return the LoanDto if found.
     * @throws LoanNotFoundException if the loan is not found.
     */
    @Override
    public LoanDto getLoanById(Long loanId) {
        LoanDto loan = loanServiceClient.getLoanByLoanId(loanId);
        if (loan == null) {
            throw new LoanNotFoundException("Loan with ID " + loanId + " not found");
        }
        return loan;
    }

    /**
     * Updates the status of a loan.
     *
     * @param loanId the unique identifier of the loan.
     * @param status the new status to set for the loan.
     * @return the updated LoanDto.
     * @throws LoanNotFoundException if the loan is not found.
     */
    @Override
    public LoanDto updateLoanStatus(Long loanId, String status) {
        LoanDto loan = loanServiceClient.updateLoanStatus(loanId, status);
        if (loan == null) {
            throw new LoanNotFoundException("Loan with ID " + loanId + " not found");
        }
        return loan;
    }
}
