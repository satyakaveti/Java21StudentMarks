package com.studentresults.event;

import java.time.LocalDateTime;

/**
 * Published after a Student is soft-deleted.
 */
public record StudentDeletedEvent(
        Long studentId,
        String studentCode,
        String performedBy,
        LocalDateTime occurredAt
) {}
