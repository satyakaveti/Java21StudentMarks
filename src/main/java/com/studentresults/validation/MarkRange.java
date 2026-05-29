package com.studentresults.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom constraint — score must be between 0 and 100 inclusive.
 * Used on CreateMarkRequest.score and UpdateMarkRequest.score.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MarkRangeValidator.class)
public @interface MarkRange {

    String message() default "Score must be between 0 and 100";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default 100;
}
