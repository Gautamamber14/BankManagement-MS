package com.example.demo.dto;

import lombok.Data;

/**
 * Data Transfer Object for User Login information.
 */
@Data
public class LoginDto {

    /**
     * The username used for login.
     */
    private String username;

    /**
     * The password used for login.
     */
    private String password;
}
