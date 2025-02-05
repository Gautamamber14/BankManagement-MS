package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.LoanServiceClient;
import com.example.demo.dto.LoanDto;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private LoanServiceClient loanServiceClient;

    
    @Override
    public List<LoanDto> getAllLoans() {
        return loanServiceClient.getAllLoan();
    }



	@Override
	public LoanDto getLoanById(Long loanId) {
		
		return loanServiceClient.getLoanByLoanId(loanId);
	}


	@Override
	public LoanDto updateLoanStatus(Long loanId, String status) {

		return loanServiceClient.updateLoanStatus(loanId,status);
	}
}
