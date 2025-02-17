package com.example.demo.exception;

/**
 * Exception thrown when the username already exists.
 */
public class UsernameAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new UsernameAlreadyExistsException with a specified message.
     *
     * @param message The detail message.
     */
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
