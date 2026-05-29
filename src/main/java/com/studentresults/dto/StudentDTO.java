package com.studentresults.dto;

import java.time.LocalDate;

/**
 * Read-only projection of Student — returned in API responses.
 * Java 16 record: immutable, compact, auto-generates equals/hashCode/toString.
 */
public record StudentDTO(
        Long id,
        String studentCode,
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth
) {}
