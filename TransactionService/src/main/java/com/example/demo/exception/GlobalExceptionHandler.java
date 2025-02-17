package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ValidationException;



/**
 * **GlobalExceptionHandler**
 *
 * This class handles exceptions globally across the entire application.
 * It's annotated with `@RestControllerAdvice`, which makes it a centralized
 * exception handling component that returns responses in JSON format.
 *
 * By handling exceptions in a single place, you ensure consistent error responses
 * and reduce boilerplate code in your controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@link TransactionNotFoundException}.
     *
     * @param ex      the exception instance
     * @param request the current web request
     * @return a {@link ResponseEntity} containing the error message and an HTTP 404 status
     */
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleTransactionNotFoundException(
            TransactionNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

// 
//    /**
//     * Handles exceptions of type {@link ValidationException}.
//     *
//     * @param ex      the exception instance
//     * @param request the current web request
//     * @return a {@link ResponseEntity} containing the error message and an HTTP 400 status
//     */
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<String> handleValidationException(
//            ValidationException ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
    
    /**
     * Handles MethodArgumentNotValidException and returns a BAD_REQUEST response with validation errors.
     *
     * @param ex The exception.
     * @return A ResponseEntity with the validation error messages and BAD_REQUEST status.
     */

    /**
     * Handles MethodArgumentNotValidException and returns a BAD_REQUEST response with validation errors.
     *
     * @param ex The exception.
     * @return A ResponseEntity with the validation error messages and BAD_REQUEST status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ValidationException and returns a BAD_REQUEST response.
     *
     * @param ex The exception.
     * the error message and BAD_REQUEST status.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles HttpClientErrorException.BadRequest and returns a BAD_REQUEST response with error details.
     *
     * @param ex The exception.
     * @return A ResponseEntity with the error message and BAD_REQUEST status.
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequestException(HttpClientErrorException.BadRequest ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param ex The exception.
     * @return A ResponseEntity with the error message and INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        // You can log the exception here if needed
    	 return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
                 HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
