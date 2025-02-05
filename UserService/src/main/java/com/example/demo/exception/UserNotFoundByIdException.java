package com.example.demo.exception;

/**
 * Exception thrown when a user is not found by userId during operations that require a valid user ID.
 */
public class UserNotFoundByIdException extends RuntimeException {
    

    /**
     * Constructs a new UserNotFoundByIdException with a specified message.
     *
     * @param message The detail message.
     */
    public UserNotFoundByIdException(String message) {
        super(message);
    }
}
