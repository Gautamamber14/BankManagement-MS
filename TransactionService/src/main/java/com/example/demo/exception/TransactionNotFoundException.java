package com.example.demo.exception;

/**
 * Exception thrown when a transaction with the specified ID is not found.
 */
public class TransactionNotFoundException extends RuntimeException {

    /**
     * Constructs a new TransactionNotFoundException with a detailed message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
