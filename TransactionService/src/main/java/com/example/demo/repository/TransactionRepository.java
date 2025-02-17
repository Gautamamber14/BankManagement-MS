package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Transaction;

/**
 * **TransactionRepository**
 * 
 * Repository interface for managing {@link Transaction} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and custom query methods.
 * This interface facilitates interaction with the database for Transaction-related operations.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retrieves a list of transactions associated with a specific user.
     *
     * @param userId the unique identifier of the user whose transactions are to be retrieved.
     * @return a list of {@code Transaction} entities belonging to the specified user.
     */
    List<Transaction> findByUserId(Long userId); // Custom query method
}
