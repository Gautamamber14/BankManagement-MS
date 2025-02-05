package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * The username of the user.
     * Must not be empty and must be between 5 and 30 characters.
     */
    @NotEmpty(message = "Username is required")
    @Size(min = 5, max = 30, message = "Username must be between 5 and 30 characters")
    private String username;

    /**
     * The full name of the customer.
     * Must not be empty.
     */
    @NotEmpty(message = "Customer name is required")
    private String customername;

    /**
     * The city of the customer.
     * Must not be empty.
     */
    @NotEmpty(message = "Customer city is required")
    private String customerCity;

    /**
     * The contact number of the customer.
     * Must not be empty and must be exactly 10 digits.
     */
    @NotEmpty(message = "Customer contact number is required")
    @Size(min = 10, max = 10, message = "Customer contact number must be exactly 10 digits")
    @Digits(integer = 10, fraction = 0, message = "Customer contact number must be numeric")
    private String customerContactNo;

    /**
     * The occupation of the customer.
     * Must not be empty.
     */
    @NotEmpty(message = "Occupation is required")
    private String occupation;

    /**
     * The date of birth of the customer.
     * Must be a past date.
     */
    @Past(message = "Date of birth must be in the past")
    private LocalDate customerDateOfBirth;

    /**
     * The password for the user's account.
     * Must not be empty and at least 5 characters long.
     */
    @NotEmpty(message = "Password is required")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    /**
     * The account number associated with the user.
     * This is auto-generated and does not require validation.
     */
    private String accountNumber; // Auto-generated
}
