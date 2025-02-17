package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Transaction;
import com.example.demo.exception.TransactionNotFoundException;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionServiceImpl;

import jakarta.validation.ValidationException;

@SpringBootTest
class TransactionServiceApplicationTests {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Transaction mockTransaction;

    @BeforeEach
    void setUp() {
        mockTransaction = new Transaction();
        mockTransaction.setTransactionId(1L);
        mockTransaction.setUserId(1L);
        mockTransaction.setTransactionType("Deposit");
        mockTransaction.setAmount(new BigDecimal("1000.0"));
        mockTransaction.setTransactionDate(LocalDate.now());
        mockTransaction.setAccountNumber("ACC123456789");
        mockTransaction.setCustomerFullName("Test User");
    }

    /**
     * Test for successful transaction performance.
     */
    @Test
    void testPerformTransaction_Successful() {
        // Arrange
        when(transactionRepository.save(any(Transaction.class))).thenReturn(mockTransaction);

        // Act
        Transaction savedTransaction = transactionService.performTransaction(mockTransaction);

        // Assert
        assertNotNull(savedTransaction, "Expected saved transaction to be not null");
        assertEquals(mockTransaction.getTransactionId(), savedTransaction.getTransactionId());
        assertEquals(mockTransaction.getUserId(), savedTransaction.getUserId());
        assertEquals(mockTransaction.getTransactionType(), savedTransaction.getTransactionType());
        assertEquals(mockTransaction.getAmount(), savedTransaction.getAmount());
        assertEquals(mockTransaction.getTransactionDate(), savedTransaction.getTransactionDate());
        assertEquals(mockTransaction.getAccountNumber(), savedTransaction.getAccountNumber());
        assertEquals(mockTransaction.getCustomerFullName(), savedTransaction.getCustomerFullName());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    /**
     * Test for performing transaction with invalid data.
     */
    @Test
    void testPerformTransaction_InvalidData() {
        // Arrange
        mockTransaction.setTransactionType(null);

        // Act & Assert
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transactionService.performTransaction(mockTransaction),
                "Expected performTransaction() to throw, but it didn't"
        );

        assertEquals("Invalid transaction data: transactionType is required", thrown.getMessage());
    }

    /**
     * Test for retrieving transaction statements with valid user ID.
     */
    @Test
    void testGetTransactionStatement_Successful() {
        // Arrange
        List<Transaction> transactions = List.of(mockTransaction);
        when(transactionRepository.findByUserId(1L)).thenReturn(transactions);

        // Act
        List<Transaction> result = transactionService.getTransactionStatement(1L);

        // Assert
        assertNotNull(result, "Expected transactions list to be not null");
        assertFalse(result.isEmpty(), "Expected transactions list to be not empty");
        assertEquals(transactions.size(), result.size());
        verify(transactionRepository, times(1)).findByUserId(1L);
    }

}