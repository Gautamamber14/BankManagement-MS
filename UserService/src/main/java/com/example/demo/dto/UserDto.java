package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * Data Transfer Object for User information.
 */
@Data
public class UserDto {

    private Long userId;

    /**
     * The username of the user.
     */
    private String username;
    
    private String Role;

    /**
     * The full name of the customer.
     */
    private String customername;

    /**
     * The city where the customer resides.
     */
    private String customerCity;

    /**
     * The contact number of the customer.
     */
    private String customerContactNo;

    /**
     * The occupation of the customer.
     */
    private String occupation;

    /**
     * The date of birth of the customer.
     */
    private LocalDate customerDateOfBirth;

    /**
     * The account number associated with the user.
     */
    private String accountNumber;
}
