package com.studentresults.validation;

import com.studentresults.repository.StudentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * Validator for @UniqueRollNumber — queries the DB to check uniqueness.
 */
@Component
public class UniqueRollNumberValidator implements ConstraintValidator<UniqueRollNumber, String> {

    private final StudentRepository studentRepository;

    public UniqueRollNumberValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean isValid(String studentCode, ConstraintValidatorContext context) {
        if (studentCode == null || studentCode.isBlank()) {
            return true; // @NotBlank handles this
        }
        return !studentRepository.existsByStudentCode(studentCode);
    }
}
