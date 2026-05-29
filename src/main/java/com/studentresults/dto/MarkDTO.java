package com.studentresults.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Read-only projection of Mark — returned in API responses.
 */
public record MarkDTO(
        Long id,
        Long studentId,
        String subjectName,
        Integer score,
        String grade,
        LocalDate examDate,
        LocalDateTime updatedAt
) {}
