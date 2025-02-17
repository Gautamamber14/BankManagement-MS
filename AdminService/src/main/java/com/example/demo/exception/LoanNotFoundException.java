package com.example.demo.exception;

/**
 * Exception thrown when a loan with the specified ID is not found.
 */
public class LoanNotFoundException extends RuntimeException {

    /**
     * Constructs a new LoanNotFoundException with a detailed message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public LoanNotFoundException(String message) {
        super(message);
    }
}