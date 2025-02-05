package com.example.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loan.entity.Loan;

/**
 * Repository interface for Loan entities.
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {

    /**
     * Finds all loans associated with a specific user.
     *
     * @param userId the ID of the user
     * @return a list of loans for the user
     */
    List<Loan> findByUserId(Long userId);
    
}
