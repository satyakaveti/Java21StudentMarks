package com.studentresults.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for @MarkRange — ensures score is within [min, max].
 */
public class MarkRangeValidator implements ConstraintValidator<MarkRange, Integer> {

    private int min;
    private int max;

    @Override
    public void initialize(MarkRange annotation) {
        this.min = annotation.min();
        this.max = annotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNull handles null check separately
        }
        return value >= min && value <= max;
    }
}
