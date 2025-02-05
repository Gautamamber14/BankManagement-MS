package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LoanDto;

public interface AdminService {
	List<LoanDto> getAllLoans();
	
	LoanDto getLoanById(Long loanId);
	
	LoanDto updateLoanStatus(Long loanId, String Status);
}
