package com.studentresults.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Cross-field DB uniqueness check — studentCode must not already exist.
 * Hits StudentRepository.existsByStudentCode() during validation.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRollNumberValidator.class)
public @interface UniqueRollNumber {

    String message() default "Student code already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
