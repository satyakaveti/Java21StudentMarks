package com.studentresults.event;

import java.time.LocalDateTime;

/**
 * Published after mark is created or updated.
 * @TransactionalEventListener in NotificationService fires email after commit.
 */
public record MarksUpdatedEvent(
        Long markId,
        Long studentId,
        String subjectName,
        Integer newScore,
        String grade,
        LocalDateTime occurredAt
) {}
