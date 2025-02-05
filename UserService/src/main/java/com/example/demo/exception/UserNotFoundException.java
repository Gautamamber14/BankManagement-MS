package com.example.demo.exception;

/**
 * Exception thrown when a user is not found by username during login validation.
 */
public class UserNotFoundException extends RuntimeException {
    

    /**
     * Constructs a new UserNotFoundException with a specified message.
     *
     * @param message The detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
