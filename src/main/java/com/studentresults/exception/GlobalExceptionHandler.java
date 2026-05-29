package com.studentresults.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

/**
 * Global exception handler — all errors return RFC 7807 ProblemDetail.
 * Spring Boot 3 ships ProblemDetail natively; no extra library needed.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ProblemDetail handleStudentNotFound(StudentNotFoundException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setType(URI.create("/errors/student-not-found"));
        problem.setTitle("Student Not Found");
        return problem;
    }

    @ExceptionHandler(MarkNotFoundException.class)
    public ProblemDetail handleMarkNotFound(MarkNotFoundException ex) {
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setType(URI.create("/errors/mark-not-found"));
        problem.setTitle("Mark Not Found");
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        var problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Validation failed");
        problem.setType(URI.create("/errors/validation-error"));
        problem.setTitle("Validation Error");
        // TODO: populate field-level errors into problem.setProperty(...)
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneric(Exception ex) {
        var problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        problem.setType(URI.create("/errors/internal-error"));
        problem.setTitle("Internal Server Error");
        return problem;
    }
}
