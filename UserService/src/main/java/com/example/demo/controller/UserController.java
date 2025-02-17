package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LoanDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Handles user login requests.
     * Validates user credentials and returns a success message if valid.
     *
     * @param loginDTO The {@link LoginDto} containing username and password.
     * @return A {@link ResponseEntity} with a success message and HTTP status.
     * @throws UserNotFoundException if the user is not found or credentials are invalid.
     */
    @PostMapping("/login") // Endpoint: http://localhost:8081/users/login
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDto loginDTO) {
        try {
            userService.validateUser(loginDTO);
            return new ResponseEntity<>("Login successful, redirecting to menu page...", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Registers a new user.
     * Accepts user details and saves the user to the database.
     *
     * @param user The {@link User} entity containing user details.
     * @return A {@link ResponseEntity} containing the registered user and HTTP status.
     */
    @PostMapping("/register") // Endpoint: http://localhost:8081/users/register
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    /**
     * Applies for a loan by user ID.
     * Processes a loan application for the specified user.
     *
     * @param loan   The {@link LoanDto} containing loan details.
     * @param userId The ID of the user applying for the loan.
     * @return A {@link ResponseEntity} containing the applied loan and HTTP status.
     */
    @PostMapping("/applyLoanByUserId/{userId}") // Endpoint: http://localhost:8081/users/applyLoanByUserId/{userId}
    public ResponseEntity<LoanDto> applyLoan(@Valid @RequestBody LoanDto loan, @PathVariable Long userId) {
        LoanDto appliedLoan = userService.applyLoan(loan, userId);
        return new ResponseEntity<>(appliedLoan, HttpStatus.CREATED);
    }

    /**
     * Retrieves all loans for a specific user.
     * Fetches the loans associated with the given user ID.
     *
     * @param userId The ID of the user whose loans are to be retrieved.
     * @return A {@link ResponseEntity} containing the list of loans and HTTP status.
     */
    @GetMapping("/getLoansByUserId/{userId}") // Endpoint: http://localhost:8081/users/getLoansByUserId/{userId}
    public ResponseEntity<List<LoanDto>> getLoansByUserId(@PathVariable Long userId) {
        List<LoanDto> loanList = userService.getLoansByUserId(userId);
        return new ResponseEntity<>(loanList, HttpStatus.OK);
    }
    
    /**
     * Performs a transaction for a specific user.
     * Processes a deposit or withdrawal transaction for the user.
     *
     * @param transaction The {@link TransactionDto} containing transaction details.
     * @param userId      The ID of the user performing the transaction.
     * @return A {@link ResponseEntity} containing the performed transaction and HTTP status.
     */
    @PostMapping("/performtransaction/{userId}") // Endpoint: http://localhost:8081/users/performtransaction/{userId}
    public ResponseEntity<TransactionDto> performTransactionByUserId(@Valid @RequestBody TransactionDto transaction, @PathVariable Long userId) {
        TransactionDto performTransaction = userService.performTransactionByUserId(transaction, userId);
        return new ResponseEntity<>(performTransaction, HttpStatus.CREATED);
    }
    

    /**
     * Retrieves all transactions for a specific user.
     * Fetches the transactions associated with the given user ID.
     *
     * @param userId The ID of the user whose transactions are to be retrieved.
     * @return A {@link ResponseEntity} containing the list of transactions and HTTP status.
     */
    @GetMapping("/transactions/{userId}") // Endpoint: http://localhost:8081/users/transactions/{userId}
    public ResponseEntity<List<TransactionDto>> getTransactionsByUserId(@PathVariable Long userId) {
        List<TransactionDto> transactionList = userService.getTransactionsByUserId(userId);
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }


}
