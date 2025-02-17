package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.demo.dto.LoanDto;

/**
 * **LoanServiceClient**
 *
 * Feign client interface for communicating with the **Loan Service**.
 * Provides methods to retrieve loan information and update loan statuses.
 *
 * **Base URL:** `http://localhost:8082/loans/`
 */
@FeignClient(name = "LoanService") // Base URL for the Loan Service
public interface LoanServiceClient {

    /**
     * Retrieves a list of all loans.
     *
     * **Endpoint:** `GET /loans/getAllLoan`
     *
     * @return a list of {@code LoanDto} objects representing all loans.
     */
    @GetMapping("/loans/getAllLoan")
    List<LoanDto> getAllLoan();

    /**
     * Retrieves a loan by its unique identifier.
     *
     * **Endpoint:** `GET /loans/{loanId}`
     *
     * @param loanId the unique ID of the loan to retrieve.
     * @return a {@code LoanDto} representing the loan.
     * @throws LoanNotFoundException if no loan is found with the given ID.
     */
    @GetMapping("/loans/{loanId}")
    LoanDto getLoanByLoanId(@PathVariable("loanId") Long loanId);

    /**
     * Updates the status of a loan.
     *
     * **Endpoint:** `PUT /loans/update/{loanId}/{status}`
     *
     * @param loanId the unique ID of the loan to update.
     * @param status the new status to set for the loan.
     * @return the updated {@code LoanDto} object.
     * @throws LoanNotFoundException if no loan is found with the given ID.
     */
    @PutMapping("/loans/update/{loanId}/{status}")
    LoanDto updateLoanStatus(@PathVariable("loanId") Long loanId, @PathVariable("status") String status);

}
