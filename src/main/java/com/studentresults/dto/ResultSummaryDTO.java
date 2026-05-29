package com.studentresults.dto;

import java.util.List;

/**
 * Aggregate result summary for a student.
 * Used by GET /api/results/me/summary
 */
public record ResultSummaryDTO(
        Long studentId,
        String studentCode,
        String fullName,
        List<MarkDTO> marks,
        double average,
        int totalScore,
        int rank,
        boolean overallPass
) {}
