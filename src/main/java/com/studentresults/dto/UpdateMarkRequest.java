package com.studentresults.dto;

import com.studentresults.validation.MarkRange;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

/**
 * Request body for PUT /api/admin/marks/{markId}
 */
public record UpdateMarkRequest(

        @NotNull(message = "Score is required")
        @MarkRange
        Integer score,

        @NotNull(message = "Exam date is required")
        @PastOrPresent(message = "Exam date cannot be in the future")
        LocalDate examDate

) {}
