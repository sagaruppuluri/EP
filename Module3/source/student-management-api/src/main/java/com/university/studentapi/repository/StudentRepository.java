package com.university.studentapi.repository;

import com.university.studentapi.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory repository for Student entities.
 * Uses ConcurrentHashMap for thread-safe operations.
 */
@Repository
public class StudentRepository {
    
    private final Map<String, Student> students = new ConcurrentHashMap<>();

    /**
     * Save a student (create or update)
     */
    public Student save(Student student) {
        students.put(student.getStudentNumber(), student);
        return student;
    }

    /**
     * Find student by student number
     */
    public Optional<Student> findByStudentNumber(String studentNumber) {
        return Optional.ofNullable(students.get(studentNumber));
    }

    /**
     * Check if student exists by student number
     */
    public boolean existsByStudentNumber(String studentNumber) {
        return students.containsKey(studentNumber);
    }

    /**
     * Find all students
     */
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    /**
     * Delete student by student number
     */
    public void deleteByStudentNumber(String studentNumber) {
        students.remove(studentNumber);
    }

    /**
     * Delete all students
     */
    public void deleteAll() {
        students.clear();
    }

    /**
     * Count total students
     */
    public long count() {
        return students.size();
    }

    /**
     * Find students by name (partial match, case-insensitive)
     */
    public List<Student> findByNameContaining(String name) {
        return students.values().stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Find students by city
     */
    public List<Student> findByCity(String city) {
        return students.values().stream()
                .filter(s -> s.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    /**
     * Find students with CGPA greater than or equal to minimum
     */
    public List<Student> findByCgpaGreaterThanEqual(Double minCgpa) {
        return students.values().stream()
                .filter(s -> s.getCgpa() >= minCgpa)
                .collect(Collectors.toList());
    }

    /**
     * Find students with no backlogs
     */
    public List<Student> findByBacklogsEquals(Integer backlogs) {
        return students.values().stream()
                .filter(s -> s.getBacklogs().equals(backlogs))
                .collect(Collectors.toList());
    }
}
