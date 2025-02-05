package com.example.demo.externalClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.LoanDto;

@FeignClient(name = "LoanService")
public interface LoanClient {

	/**
	 * Applies for a new loan.
	 *
	 * @param loan The {@link LoanDto} containing loan details to be applied.
	 * @return The applied {@link LoanDto} with updated information.
	 * @see LoanDto
	 */
	@PostMapping("/loans/apply") // http://localhost:8082/loans/apply
	LoanDto applyForLoan(@RequestBody LoanDto loan);

	/**
	 * Retrieves all loans associated with a specific user ID.
	 *
	 * @param userId The ID of the user whose loans are to be retrieved.
	 * @return A list of {@link LoanDto} objects representing the user's loans.
	 * @see LoanDto
	 */
	@GetMapping("/loans/getAllLoansByUserId/{userId}")
	List<LoanDto> getAllLoansByUserId(@PathVariable Long userId);

	/**
	 * Retrieves loan details by loan ID.
	 *
	 * @param loanId The ID of the loan to retrieve.
	 * @return The {@link LoanDto} containing loan details.
	 * @see LoanDto
	 */
	@GetMapping("/loans/{loanId}")
	LoanDto getLoanByLoanId(@PathVariable Long loanId);
}
