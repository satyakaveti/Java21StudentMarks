package com.studentresults.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Request body for PUT /api/admin/students/{id}
 * studentCode is immutable — not accepted here.
 */
public record UpdateStudentRequest(

        @NotBlank(message = "First name is required")
        @Size(max = 100)
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100)
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Must be a valid email address")
        String email,

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth

) {}
