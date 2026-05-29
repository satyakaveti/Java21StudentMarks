package com.studentresults.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Sends daily result digest email to all active students.
 * Runs Mon–Fri at 07:00.
 */
@Component
public class DigestScheduler {

    private static final Logger log = LoggerFactory.getLogger(DigestScheduler.class);

    // TODO: inject StudentRepository, NotificationService, JavaMailSender

    /** Daily digest — Mon–Fri 07:00 */
    @Scheduled(cron = "0 0 7 * * MON-FRI")
    public void sendDailyDigest() {
        // TODO: fetch all active students, send result summary email to each
        log.info("DigestScheduler: sendDailyDigest triggered");
    }

    /** Cleanup expired JWT blacklist entries every 60 seconds */
    @Scheduled(fixedDelay = 60_000)
    public void cleanupExpiredJwtBlacklist() {
        // TODO: remove entries older than JWT expiry from blacklist store (Redis)
        log.debug("DigestScheduler: cleanupExpiredJwtBlacklist triggered");
    }
}
