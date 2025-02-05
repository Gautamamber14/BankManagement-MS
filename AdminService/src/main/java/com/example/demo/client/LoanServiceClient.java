package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.demo.dto.LoanDto;

@FeignClient(name = "LoanService") // http://localhost:8082/loans/
public interface LoanServiceClient {

	@GetMapping("/loans/getAllLoan")
	public List<LoanDto> getAllLoan();

	@GetMapping("/loans/{loanId}")
	public LoanDto getLoanByLoanId(@PathVariable Long loanId);

	@PutMapping("/loans/update/{loanId}/{status}")
	public LoanDto updateLoanStatus(@PathVariable Long loanId, @PathVariable String status);

}
