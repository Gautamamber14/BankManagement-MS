package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler to manage exceptions across the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles UserNotFoundException and returns a NOT_FOUND response.
	 *
	 * @param ex The exception.
	 * @return A ResponseEntity with the error message and NOT_FOUND status.
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles UserNotFoundByIdException and returns a NOT_FOUND response.
	 *
	 * @param ex The exception.
	 * @return A ResponseEntity with the error message and NOT_FOUND status.
	 */
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<String> handleUserNotFoundByIdException(UserNotFoundByIdException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}



	  /**
     * Handles UsernameAlreadyExistsException and returns a CONFLICT response.
     *
     * @param ex The exception.
     * @return A ResponseEntity with the error message and CONFLICT status.
     */
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
	
	/**
	 * Handles all other exceptions and returns an INTERNAL_SERVER_ERROR response.
	 *
	 * @param ex The exception.
	 * @return A ResponseEntity with the error message and INTERNAL_SERVER_ERROR
	 *         status.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex) {
		// You can log the exception here if needed
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
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
}
