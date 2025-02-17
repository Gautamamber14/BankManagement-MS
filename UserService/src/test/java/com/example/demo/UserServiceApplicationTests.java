package com.example.demo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.LoanDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundByIdException;
import com.example.demo.exception.UsernameAlreadyExistsException;
import com.example.demo.externalClients.LoanClient;
import com.example.demo.externalClients.TransactionClient;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;

@SpringBootTest
class UserServiceApplicationTests {

	@Mock
	private UserRepository userRepository;

	@Mock
	private LoanClient loanClient;

	@Mock
	private TransactionClient transactionClient;

	@InjectMocks
	private UserServiceImpl userService;

	private User mockUser;

	@BeforeEach
	void setUp() {
		mockUser = new User();
		mockUser.setUserId(1L);
		mockUser.setUsername("testuser");
		mockUser.setPassword("password@123");
		mockUser.setCustomername("Test User");
		mockUser.setAccountNumber("ACC123456789");
	}

	/**
	 * Test for successful user validation.
	 */
	@Test
	void testValidateUser_Successful() {
		// Arrange
		LoginDto loginDto = new LoginDto("testuser", "password@123");
		when(userRepository.findByUsername("testuser")).thenReturn(mockUser);

		// Act
		boolean isValid = userService.validateUser(loginDto);

		// Assert
		assertTrue(isValid, "true");
		verify(userRepository, times(1)).findByUsername("testuser");
	}

	/**
	 * Test for user registration with an existing username.
	 */
	@Test
	void testRegisterUser_UsernameAlreadyExists() {
		// Arrange
		when(userRepository.existsByUsername(mockUser.getUsername())).thenReturn(true);

		// Act & Assert
		UsernameAlreadyExistsException thrown = assertThrows(UsernameAlreadyExistsException.class,
				() -> userService.registerUser(mockUser), "Expected registerUser() to throw, but it didn't");

		assertEquals("Username already exists: testuser", thrown.getMessage());
		verify(userRepository, times(1)).existsByUsername(mockUser.getUsername());
	}

	/**
	 * Test for successful user registration.
	 */
	@Test
	void testRegisterUser_Successful() {
		// Arrange
		when(userRepository.existsByUsername(mockUser.getUsername())).thenReturn(false);
		when(userRepository.save(mockUser)).thenReturn(mockUser);

		// Act
		User registeredUser = userService.registerUser(mockUser);

		// Assert
		assertNotNull(registeredUser);
		assertEquals(mockUser.getUsername(), registeredUser.getUsername());
	}

	/**
     * Test for applying loan with non-existing user ID.
     */
    @Test
    void testApplyLoan_UserNotFound() {
        // Arrange
        Long userId = 1L;
        LoanDto loanDto = new LoanDto();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        UserNotFoundByIdException thrown = assertThrows(
                UserNotFoundByIdException.class,
                () -> userService.applyLoan(loanDto, userId),
                "Expected applyLoan() to throw, but it didn't"
        );

        assertEquals("User not found with ID: 1", thrown.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }
}
