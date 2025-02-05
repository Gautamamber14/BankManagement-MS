package com.example.demo.exception;

/**
 * Exception thrown when a validation error occurs.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructs a new ValidationException with a specified message.
     *
     * @param message The detail message.
     */
    public ValidationException(String message) {
        super(message);
    }
}
