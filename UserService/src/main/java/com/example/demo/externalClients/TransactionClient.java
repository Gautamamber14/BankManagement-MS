package com.example.demo.externalClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.TransactionDto;

@FeignClient(name = "TRANSACTIONSERVICE")
public interface TransactionClient {

	/**
	 * Performs a financial transaction (withdrawal or deposit).
	 *
	 * @param transaction The {@link TransactionDto} containing transaction details.
	 * @return The {@link TransactionDto} after the transaction is completed.
	 * @see TransactionDto
	 */
	@PostMapping("/transactions/perform") // http://localhost:8083/transactions/perform
	public TransactionDto performTransaction(@RequestBody TransactionDto transaction);

	/**
	 * Retrieves all transactions associated with a specific user ID.
	 *
	 * @param userId The ID of the user whose transactions are to be retrieved.
	 * @return A list of {@link TransactionDto} objects representing the user's
	 *         transaction history.
	 * @see TransactionDto
	 */
	@GetMapping("/transactions/statement/{userId}") // http://localhost:8083/transactions/statement/{userId}
	public List<TransactionDto> getTransactionsByUserId(@PathVariable Long userId);
}
