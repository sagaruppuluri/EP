package com.university.studentapi.service;

import com.university.studentapi.dto.*;
import com.university.studentapi.exception.DuplicateStudentException;
import com.university.studentapi.exception.StudentNotFoundException;
import com.university.studentapi.model.Student;
import com.university.studentapi.repository.StudentRepository;
import com.university.studentapi.util.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service layer for student business logic
 */
@Service
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * Get all students with pagination and filtering
     */
    public StudentPageResponse getAllStudents(
            int page,
            int size,
            String name,
            Double minCgpa,
            String city,
            String sortBy,
            String sortOrder) {

        log.info("Fetching students: page={}, size={}, name={}, minCgpa={}, city={}, sortBy={}, sortOrder={}",
                page, size, name, minCgpa, city, sortBy, sortOrder);

        List<Student> allStudents = studentRepository.findAll();

        // Apply filters
        List<Student> filteredStudents = allStudents.stream()
                .filter(s -> name == null || s.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(s -> minCgpa == null || s.getCgpa() >= minCgpa)
                .filter(s -> city == null || s.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

        // Apply sorting
        Comparator<Student> comparator = getComparator(sortBy);
        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }
        filteredStudents.sort(comparator);

        // Apply pagination
        int totalElements = filteredStudents.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, totalElements);

        List<Student> pageContent = fromIndex < totalElements
                ? filteredStudents.subList(fromIndex, toIndex)
                : Collections.emptyList();

        List<StudentResponse> responseContent = pageContent.stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());

        StudentPageResponse.PageInfo pageInfo = StudentPageResponse.PageInfo.builder()
                .number(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();

        return StudentPageResponse.builder()
                .content(responseContent)
                .page(pageInfo)
                .build();
    }

    /**
     * Get student by student number
     */
    public StudentResponse getStudentByNumber(String studentNumber) {
        log.info("Fetching student with number: {}", studentNumber);

        Student student = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new StudentNotFoundException(studentNumber, true));

        return studentMapper.toResponse(student);
    }

    /**
     * Create new student
     */
    public StudentResponse createStudent(StudentCreateRequest request) {
        log.info("Creating new student with number: {}", request.getStudentNumber());

        // Check for duplicate
        if (studentRepository.existsByStudentNumber(request.getStudentNumber())) {
            throw new DuplicateStudentException(request.getStudentNumber(), true);
        }

        Student student = studentMapper.toEntity(request);
        student.setCreatedDate(LocalDateTime.now());
        student.setLastModifiedDate(LocalDateTime.now());

        Student savedStudent = studentRepository.save(student);
        log.info("Student created successfully: {}", savedStudent.getStudentNumber());

        return studentMapper.toResponse(savedStudent);
    }

    /**
     * Update student (full update)
     */
    public StudentResponse updateStudent(String studentNumber, StudentUpdateRequest request) {
        log.info("Updating student: {}", studentNumber);

        Student existingStudent = studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new StudentNotFoundException(studentNumber, true));

        studentMapper.updateEntity(request, existingStudent);
        existingStudent.setLastModifiedDate(LocalDateTime.now());

        Student updatedStudent = studentRepository.save(existingStudent);
        log.info("Student updated successfully: {}", studentNumber);

        return studentMapper.toResponse(updatedStudent);
    }

    /**
     * Partial update student
     */
    public StudentResponse partialUpdateStudent(String studentNumber, StudentUpdateRequest request) {
        log.info("Partially updating student: {}", studentNumber);
        
        // Reuse the same update logic as full update since we check for nulls in mapper
        return updateStudent(studentNumber, request);
    }

    /**
     * Delete student
     */
    public void deleteStudent(String studentNumber) {
        log.info("Deleting student: {}", studentNumber);

        if (!studentRepository.existsByStudentNumber(studentNumber)) {
            throw new StudentNotFoundException(studentNumber, true);
        }

        studentRepository.deleteByStudentNumber(studentNumber);
        log.info("Student deleted successfully: {}", studentNumber);
    }

    /**
     * Advanced search with multiple criteria
     */
    public List<StudentResponse> advancedSearch(
            String name,
            String city,
            String state,
            String country,
            Double minCgpa,
            Double maxCgpa,
            Integer maxBacklogs) {

        log.info("Performing advanced search with criteria");

        List<Student> results = studentRepository.findAll().stream()
                .filter(s -> name == null || s.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(s -> city == null || s.getAddress().getCity().equalsIgnoreCase(city))
                .filter(s -> state == null || s.getAddress().getState().equalsIgnoreCase(state))
                .filter(s -> country == null || s.getAddress().getCountry().equalsIgnoreCase(country))
                .filter(s -> minCgpa == null || s.getCgpa() >= minCgpa)
                .filter(s -> maxCgpa == null || s.getCgpa() <= maxCgpa)
                .filter(s -> maxBacklogs == null || s.getBacklogs() <= maxBacklogs)
                .collect(Collectors.toList());

        return results.stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get student statistics
     */
    public StudentStatistics getStatistics() {
        log.info("Calculating student statistics");

        List<Student> allStudents = studentRepository.findAll();
        
        if (allStudents.isEmpty()) {
            return StudentStatistics.builder()
                    .totalStudents(0)
                    .averageCgpa(0.0)
                    .studentsWithNoBacklogs(0)
                    .studentsWithBacklogs(0)
                    .topPerformers(0)
                    .cityDistribution(Collections.emptyMap())
                    .build();
        }

        long totalStudents = allStudents.size();
        double averageCgpa = allStudents.stream()
                .mapToDouble(Student::getCgpa)
                .average()
                .orElse(0.0);

        long noBacklogs = allStudents.stream()
                .filter(s -> s.getBacklogs() == 0)
                .count();

        long withBacklogs = totalStudents - noBacklogs;

        long topPerformers = allStudents.stream()
                .filter(s -> s.getCgpa() >= 9.0)
                .count();

        Map<String, Long> cityDistribution = allStudents.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getAddress().getCity(),
                        Collectors.counting()
                ));

        return StudentStatistics.builder()
                .totalStudents(totalStudents)
                .averageCgpa(Math.round(averageCgpa * 100.0) / 100.0)
                .studentsWithNoBacklogs(noBacklogs)
                .studentsWithBacklogs(withBacklogs)
                .topPerformers(topPerformers)
                .cityDistribution(cityDistribution)
                .build();
    }

    /**
     * Get comparator based on sort field
     */
    private Comparator<Student> getComparator(String sortBy) {
        if (sortBy == null) {
            sortBy = "studentNumber";
        }

        return switch (sortBy.toLowerCase()) {
            case "name" -> Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER);
            case "cgpa" -> Comparator.comparing(Student::getCgpa);
            case "createddate" -> Comparator.comparing(Student::getCreatedDate);
            default -> Comparator.comparing(Student::getStudentNumber);
        };
    }
}
