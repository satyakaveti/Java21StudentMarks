package com.studentresults.dto;

import com.studentresults.validation.MarkRange;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

/**
 * Request body for POST /api/admin/students/{id}/marks
 * @MarkRange is a custom constraint — score must be 0–100.
 */
public record CreateMarkRequest(

        @NotNull(message = "Subject ID is required")
        Long subjectId,

        @NotNull(message = "Score is required")
        @MarkRange
        Integer score,

        @NotNull(message = "Exam date is required")
        @PastOrPresent(message = "Exam date cannot be in the future")
        LocalDate examDate

) {}
