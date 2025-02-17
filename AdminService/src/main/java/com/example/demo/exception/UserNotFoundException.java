package com.example.demo.exception;

/**
 * Exception thrown when a user with the specified ID is not found.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with a detailed message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}