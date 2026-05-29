package com.studentresults.service;

import com.studentresults.event.MarksUpdatedEvent;
import com.studentresults.event.StudentCreatedEvent;
import com.studentresults.event.StudentDeletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class NotificationService {

    // TODO: inject JavaMailSender, AppProperties, SseEmitter registry

    /** Fires only after the DB transaction commits — never sends email on rollback. */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void onMarksUpdated(MarksUpdatedEvent event) {
        // TODO: send marks-published email + push SSE event
    }

    @EventListener
    public void onStudentCreated(StudentCreatedEvent event) {
        // TODO: write audit log entry synchronously
    }

    @EventListener
    public void onStudentDeleted(StudentDeletedEvent event) {
        // TODO: write audit log entry synchronously
    }
}
