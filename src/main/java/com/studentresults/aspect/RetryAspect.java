package com.studentresults.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.stereotype.Component;

/**
 * Retry aspect — retries on transient DB exceptions with exponential backoff.
 * Max 3 attempts; backoff: 100ms, 200ms, 400ms.
 */
@Aspect
@Component
public class RetryAspect {

    private static final int MAX_ATTEMPTS = 3;

    @Around("execution(* com.studentresults.service..*(..)) && @annotation(com.studentresults.aspect.Auditable)")
    public Object retryOnTransientFailure(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO: implement retry loop with backoff on TransientDataAccessException
        return joinPoint.proceed();
    }
}
