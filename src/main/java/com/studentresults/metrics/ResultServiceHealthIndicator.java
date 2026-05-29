package com.studentresults.metrics;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * Custom health indicator for the result service.
 * Exposed at /actuator/health with key "resultService".
 */
@Component("resultService")
public class ResultServiceHealthIndicator extends AbstractHealthIndicator {

    // TODO: inject MarkRepository to count total marks as a sanity check

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        // TODO: implement
        //  - query total mark count
        //  - if DB accessible: builder.up().withDetail("totalMarks", count)
        //  - if exception: builder.down().withException(ex)
        builder.up().withDetail("status", "ResultService is healthy");
    }
}
