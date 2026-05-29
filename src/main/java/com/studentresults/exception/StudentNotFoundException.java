package com.studentresults.exception;

public class StudentNotFoundException extends RuntimeException {

    private final Long studentId;

    public StudentNotFoundException(Long id) {
        super("Student not found with id: " + id);
        this.studentId = id;
    }

    public StudentNotFoundException(String studentCode) {
        super("Student not found with code: " + studentCode);
        this.studentId = null;
    }

    public Long getStudentId() {
        return studentId;
    }
}
