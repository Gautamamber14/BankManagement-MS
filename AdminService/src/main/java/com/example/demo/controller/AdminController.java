package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoanDto;
import com.example.demo.service.AdminServiceImpl;

/**
 * **AdminController**
 *
 * Handles administrative operations related to loans.
 * Provides endpoints for retrieving all loans, fetching a loan by ID,
 * and updating the status of a loan.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    /**
     * Retrieves all loans.
     *
     * @return ResponseEntity containing the list of all loans and an HTTP status code.
     * @see LoanDto
     *
     * **Endpoint:** `GET /admin/getAllLoan`
     */
    @GetMapping("/getAllLoan")
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        // Fetch all loans using the admin service
        List<LoanDto> loans = adminService.getAllLoans();
        // Return the list of loans with an HTTP 200 OK status
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    /**
     * Retrieves a specific loan by its ID.
     *
     * @param loanId the unique identifier of the loan
     * @return the LoanDto object representing the loan
     * @throws LoanNotFoundException if the loan is not found
     *
     * **Endpoint:** `GET /admin/loans/{loanId}`
     */
    @GetMapping("/loans/{loanId}")
    public LoanDto getLoanByLoanId(@PathVariable Long loanId) {
        // Fetch the loan by ID using the admin service
        return adminService.getLoanById(loanId);
    }

    /**
     * Updates the status of a loan.
     *
     * @param loanId the unique identifier of the loan to update
     * @param status the new status to set for the loan
     * @return the updated LoanDto object
     * @throws LoanNotFoundException if the loan is not found
     *
     * **Endpoint:** `PUT /admin/updatestatus/{loanId}/{status}`
     */
    @PutMapping("/updatestatus/{loanId}")
    public LoanDto updateLoanStatus(@PathVariable Long loanId, @RequestParam String status) {
        // Update the loan status using the admin service
        return adminService.updateLoanStatus(loanId, status);
    }
}
