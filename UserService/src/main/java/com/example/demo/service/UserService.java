package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LoanDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.User;

public interface UserService {

    /**
     * Validates user credentials during login.
     *
     * @param loginDTO the {@link LoginDto} containing the username and password.
     * @return {@code true} if validation is successful.
     * @throws UserNotFoundException if the user is not found or credentials are invalid.
     */
    boolean validateUser(LoginDto loginDTO);

    /**
     * Registers a new user in the system.
     *
     * @param user the {@link User} entity containing user details.
     * @return the saved {@link User} entity with generated fields populated.
     * @throws ValidationException if the user data fails validation checks.
     */
    User registerUser(User user);
    
	

    /**
     * Applies for a loan on behalf of the user.
     *
     * @param loan   the {@link LoanDto} containing loan application details.
     * @param userId the ID of the user applying for the loan.
     * @return the {@link LoanDto} returned by the Loan Service after processing.
     * @throws UserNotFoundByIdException if the user with the specified ID does not exist.
     * @throws ValidationException if the loan application data is invalid.
     */
    LoanDto applyLoan(LoanDto loan, Long userId);

    /**
     * Retrieves all loans associated with a specific user.
     *
     * @param userId the ID of the user whose loans are to be retrieved.
     * @return a list of {@link LoanDto} objects representing the user's loans.
     * @throws UserNotFoundByIdException if the user with the specified ID does not exist.
     */
    List<LoanDto> getLoansByUserId(long userId);

    /**
     * Performs a financial transaction for the user.
     *
     * @param transaction the {@link TransactionDto} containing transaction details.
     * @param userId      the ID of the user performing the transaction.
     * @return the {@link TransactionDto} returned by the Transaction Service after processing.
     * @throws UserNotFoundByIdException if the user with the specified ID does not exist.
     * @throws ValidationException if the transaction data is invalid.
     */
    TransactionDto performTransactionByUserId(TransactionDto transaction, Long userId);

    /**
     * Retrieves all transactions associated with a specific user.
     *
     * @param userId the ID of the user whose transactions are to be retrieved.
     * @return a list of {@link TransactionDto} objects representing the user's transaction history.
     * @throws UserNotFoundByIdException if the user with the specified ID does not exist.
     */
    List<TransactionDto> getTransactionsByUserId(long userId);


}
