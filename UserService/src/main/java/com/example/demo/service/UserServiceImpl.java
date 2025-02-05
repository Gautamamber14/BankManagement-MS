package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LoanDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundByIdException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.externalClients.LoanClient;
import com.example.demo.externalClients.TransactionClient;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private LoanClient loanClient;
	private TransactionClient transactionClient;

	/**
	 * Validates the user credentials during login.
	 *
	 * @param loginDTO The login data transfer object containing username and
	 *                 password.
	 * @return {@code true} if validation is successful.
	 * @throws UserNotFoundException if the username does not exist or the password
	 *                               is incorrect.
	 */
	@Override
	public boolean validateUser(LoginDto loginDTO) {
		User user = userRepository.findByUsername(loginDTO.getUsername());
		if (user == null || !user.getPassword().equals(loginDTO.getPassword())) {
			throw new UserNotFoundException("Login failed: User not found or password incorrect.");
		}
		return true;
	}

	/**
	 * Registers a new user in the system.
	 *
	 * @param user The user entity containing user details.
	 * @return The saved {@link User} entity with generated account number.
	 */
	@Override
	public User registerUser(User user) {
		user.setAccountNumber(generateAccountNumber());
		return userRepository.save(user);
	}

	/**
	 * Generates a unique account number for a user.
	 *
	 * @return A 12-character alphanumeric account number.
	 */
	private String generateAccountNumber() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
	}

	/**
	 * Applies for a loan on behalf of the user.
	 *
	 * @param loan   The loan data transfer object containing loan details.
	 * @param userId The ID of the user applying for the loan.
	 * @return The {@link LoanDto} returned by the Loan Service after application.
	 * @throws UserNotFoundByIdException if the user is not found by the provided
	 *                                   {@code userId}.
	 */
	@Override
	public LoanDto applyLoan(LoanDto loan, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with ID: " + userId));
		loan.setStatus("pending");
		loan.setUserId(userId);
		loan.setCustomerFullName(user.getCustomername());
		loan.setAccountNumber(user.getAccountNumber());
		loan.setApplicationDate(LocalDate.now());

		return loanClient.applyForLoan(loan);
	}

	/**
	 * Retrieves all loans associated with a specific user.
	 *
	 * @param userId The ID of the user whose loans are to be retrieved.
	 * @return A list of {@link LoanDto} objects representing the user's loans.
	 */
	@Override
	public List<LoanDto> getLoansByUserId(long userId) {
		return loanClient.getAllLoansByUserId(userId);
	}

	/**
	 * Retrieves all transactions associated with a specific user.
	 *
	 * @param userId The ID of the user whose transactions are to be retrieved.
	 * @return A list of {@link TransactionDto} objects representing the user's
	 *         transactions.
	 */
	@Override
	public List<TransactionDto> getTransactionsByUserId(long userId) {
		return transactionClient.getTransactionsByUserId(userId);
	}

	/**
	 * Performs a financial transaction (withdrawal or deposit) for a user.
	 *
	 * @param transaction The transaction data transfer object containing
	 *                    transaction details.
	 * @param userId      The ID of the user performing the transaction.
	 * @return The {@link TransactionDto} returned by the Transaction Service after
	 *         processing.
	 * @throws UserNotFoundByIdException if the user is not found by the provided
	 *                                   {@code userId}.
	 */
	@Override
	public TransactionDto performTransactionByUserId(TransactionDto transaction, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("User not found with ID: " + userId));
		transaction.setUserId(userId);
		transaction.setAccountNumber(user.getAccountNumber());
		transaction.setTransactionDate(LocalDate.now());
		return transactionClient.performTransaction(transaction);
	}
}
