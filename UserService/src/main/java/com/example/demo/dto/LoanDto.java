package com.example.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Loan information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

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
	 * The ID of the user applying for the loan.
	 */
	private Long userId;

	/**
	 * The status of the loan application (e.g., Pending, Approved, Rejected).
	 */
	private String status;

	/**
	 * The date when the loan application was made.
	 */
	private LocalDate applicationDate;

	/**
	 * The account number associated with the user's loan.
	 */
	private String accountNumber;
}
