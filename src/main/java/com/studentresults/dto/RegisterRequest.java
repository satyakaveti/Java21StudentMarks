package com.studentresults.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request body for POST /api/auth/register (self-registration — student role)
 */
public record RegisterRequest(

        @NotBlank
        @Size(max = 100)
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password

) {}
