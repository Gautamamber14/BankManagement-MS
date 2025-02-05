
package com.example.demo.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
	private Long loanId;
    private String customerFullName;
    private String loanType;
    private double amount;
    private Long userId;
    private String status; // Pending, Approved, Rejected
    private LocalDate applicationDate;
}

