package com.studentresults.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

/**
 * Custom Micrometer metrics for the results portal.
 *
 * Metrics exposed:
 *   - student_results_marks_created_total   (Counter)
 *   - student_results_marks_updated_total   (Counter)
 *   - student_results_marks_deleted_total   (Counter)
 *   - student_results_fetch_latency_seconds (Timer)
 */
@Component
public class ResultMetrics {

    private final Counter marksCreatedCounter;
    private final Counter marksUpdatedCounter;
    private final Counter marksDeletedCounter;
    private final Timer   resultFetchTimer;

    public ResultMetrics(MeterRegistry registry) {
        this.marksCreatedCounter = Counter.builder("student_results_marks_created_total")
                .description("Total number of marks created")
                .register(registry);

        this.marksUpdatedCounter = Counter.builder("student_results_marks_updated_total")
                .description("Total number of marks updated")
                .register(registry);

        this.marksDeletedCounter = Counter.builder("student_results_marks_deleted_total")
                .description("Total number of marks deleted")
                .register(registry);

        this.resultFetchTimer = Timer.builder("student_results_fetch_latency_seconds")
                .description("Latency of result fetch operations")
                .register(registry);
    }

    public void incrementMarksCreated() { marksCreatedCounter.increment(); }
    public void incrementMarksUpdated() { marksUpdatedCounter.increment(); }
    public void incrementMarksDeleted() { marksDeletedCounter.increment(); }
    public Timer getResultFetchTimer()  { return resultFetchTimer; }
}
