package com.studentresults.exception;

public class MarkNotFoundException extends RuntimeException {

    public MarkNotFoundException(Long markId) {
        super("Mark not found with id: " + markId);
    }
}
