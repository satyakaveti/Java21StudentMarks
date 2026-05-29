package com.studentresults.event;

import java.time.LocalDateTime;

/**
 * Published after a new Student is created.
 * @EventListener in NotificationService writes audit log synchronously.
 */
public record StudentCreatedEvent(
        Long studentId,
        String studentCode,
        String performedBy,
        LocalDateTime occurredAt
) {}
