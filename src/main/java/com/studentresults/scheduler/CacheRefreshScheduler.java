package com.studentresults.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled cache maintenance tasks.
 */
@Component
public class CacheRefreshScheduler {

    private static final Logger log = LoggerFactory.getLogger(CacheRefreshScheduler.class);

    private final CacheManager cacheManager;

    public CacheRefreshScheduler(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /** Refresh subject config cache every 5 minutes */
    @Scheduled(fixedRate = 300_000)
    public void refreshSubjectCache() {
        // TODO: evict and reload subject list cache
        log.debug("CacheRefreshScheduler: refreshSubjectCache triggered");
    }

    /** Full nightly cache flush at midnight */
    @Scheduled(cron = "0 0 0 * * *")
    public void flushAllCaches() {
        cacheManager.getCacheNames()
                .forEach(name -> {
                    var cache = cacheManager.getCache(name);
                    if (cache != null) {
                        cache.clear();
                    }
                });
        log.info("CacheRefreshScheduler: all caches flushed");
    }
}
