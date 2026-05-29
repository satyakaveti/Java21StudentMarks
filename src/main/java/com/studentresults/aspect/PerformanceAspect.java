package com.studentresults.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Performance aspect — warns when any service method exceeds 500ms.
 */
@Aspect
@Component
public class PerformanceAspect {

    private static final Logger log = LoggerFactory.getLogger(PerformanceAspect.class);
    private static final long WARN_THRESHOLD_MS = 500;

    @Pointcut("execution(* com.studentresults.service..*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object measurePerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO: implement — measure duration, log warning if > WARN_THRESHOLD_MS
        return joinPoint.proceed();
    }
}
