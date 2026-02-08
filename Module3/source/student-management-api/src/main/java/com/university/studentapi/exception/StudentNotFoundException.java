package com.university.studentapi.exception;

/**
 * Exception thrown when a student is not found
 */
public class StudentNotFoundException extends RuntimeException {
    
    public StudentNotFoundException(String message) {
        super(message);
    }
    
    public StudentNotFoundException(String studentNumber, boolean byNumber) {
        super("Student not found with student number: " + studentNumber);
    }
}
