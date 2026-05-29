package com.studentresults.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Audit aspect — wraps every @Auditable method.
 * Records entity name, action, performer, duration, and result to audit_log.
 */
@Aspect
@Component
public class AuditAspect {

    // TODO: inject AuditLogRepository, ApplicationEventPublisher

    @Pointcut("@annotation(com.studentresults.aspect.Auditable)")
    public void auditableMethods() {}

    @Around("auditableMethods()")
    public Object auditMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO: implement
        //  1. Record start time
        //  2. Proceed with target method
        //  3. On success: save AuditLog with duration
        //  4. On exception: save AuditLog with error detail, rethrow
        return joinPoint.proceed();
    }
}
