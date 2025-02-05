package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoanDto;
import com.example.demo.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;

	@GetMapping("/getAllLoan") // http://localhost:8084/admin/getAllLoan
	public ResponseEntity<List<LoanDto>> getAllLoans() {
		List<LoanDto> loans = adminService.getAllLoans();
		return new ResponseEntity<>(loans, HttpStatus.OK);
	}

	@GetMapping("/loans/{loanId}") // http://localhost:8084/admin/loans/{loanId}
	public LoanDto getLoanByLoanId(@PathVariable Long loanId) {

		return adminService.getLoanById(loanId);

	}

	@PutMapping("/updatestatus/{loanId}/{status}") // http://localhost:8084/admin/updatestatus/{loanId}/{status}
	public LoanDto updateLoanStatus(@PathVariable Long loanId, @PathVariable String status) {
		return adminService.updateLoanStatus(loanId, status);

	}

}
