package com.university.studentapi.exception;

/**
 * Exception thrown when attempting to create a student with duplicate student number
 */
public class DuplicateStudentException extends RuntimeException {
    
    public DuplicateStudentException(String message) {
        super(message);
    }
    
    public DuplicateStudentException(String studentNumber, boolean byNumber) {
        super("Student with student number " + studentNumber + " already exists");
    }
}
