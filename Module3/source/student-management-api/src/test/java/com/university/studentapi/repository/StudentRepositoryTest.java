package com.university.studentapi.repository;

import com.university.studentapi.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for StudentRepository
 */
@DisplayName("StudentRepository Tests")
class StudentRepositoryTest {

    private StudentRepository studentRepository;
    private Student testStudent;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepository();

        Student.Address address = Student.Address.builder()
                .street("123 Main St")
                .city("Mumbai")
                .state("Maharashtra")
                .country("India")
                .build();

        testStudent = Student.builder()
                .studentNumber("STU001")
                .name("John Doe")
                .address(address)
                .cgpa(8.5)
                .backlogs(0)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Should save student successfully")
    void testSave() {
        // When
        Student saved = studentRepository.save(testStudent);

        // Then
        assertNotNull(saved);
        assertEquals("STU001", saved.getStudentNumber());
    }

    @Test
    @DisplayName("Should find student by student number")
    void testFindByStudentNumber() {
        // Given
        studentRepository.save(testStudent);

        // When
        Optional<Student> found = studentRepository.findByStudentNumber("STU001");

        // Then
        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
    }

    @Test
    @DisplayName("Should return empty when student not found")
    void testFindByStudentNumber_NotFound() {
        // When
        Optional<Student> found = studentRepository.findByStudentNumber("STU999");

        // Then
        assertFalse(found.isPresent());
    }

    @Test
    @DisplayName("Should check if student exists")
    void testExistsByStudentNumber() {
        // Given
        studentRepository.save(testStudent);

        // When & Then
        assertTrue(studentRepository.existsByStudentNumber("STU001"));
        assertFalse(studentRepository.existsByStudentNumber("STU999"));
    }

    @Test
    @DisplayName("Should find all students")
    void testFindAll() {
        // Given
        studentRepository.save(testStudent);

        Student student2 = Student.builder()
                .studentNumber("STU002")
                .name("Jane Smith")
                .address(testStudent.getAddress())
                .cgpa(9.0)
                .backlogs(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        studentRepository.save(student2);

        // When
        List<Student> students = studentRepository.findAll();

        // Then
        assertEquals(2, students.size());
    }

    @Test
    @DisplayName("Should delete student by student number")
    void testDeleteByStudentNumber() {
        // Given
        studentRepository.save(testStudent);

        // When
        studentRepository.deleteByStudentNumber("STU001");

        // Then
        assertFalse(studentRepository.existsByStudentNumber("STU001"));
    }

    @Test
    @DisplayName("Should delete all students")
    void testDeleteAll() {
        // Given
        studentRepository.save(testStudent);

        // When
        studentRepository.deleteAll();

        // Then
        assertEquals(0, studentRepository.count());
    }

    @Test
    @DisplayName("Should count students")
    void testCount() {
        // Given
        studentRepository.save(testStudent);

        // When
        long count = studentRepository.count();

        // Then
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Should find students by name containing")
    void testFindByNameContaining() {
        // Given
        studentRepository.save(testStudent);

        // When
        List<Student> students = studentRepository.findByNameContaining("John");

        // Then
        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
    }

    @Test
    @DisplayName("Should find students by city")
    void testFindByCity() {
        // Given
        studentRepository.save(testStudent);

        // When
        List<Student> students = studentRepository.findByCity("Mumbai");

        // Then
        assertEquals(1, students.size());
    }

    @Test
    @DisplayName("Should find students by minimum CGPA")
    void testFindByCgpaGreaterThanEqual() {
        // Given
        studentRepository.save(testStudent);

        Student student2 = Student.builder()
                .studentNumber("STU002")
                .name("Jane Smith")
                .address(testStudent.getAddress())
                .cgpa(7.0)
                .backlogs(0)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        studentRepository.save(student2);

        // When
        List<Student> students = studentRepository.findByCgpaGreaterThanEqual(8.0);

        // Then
        assertEquals(1, students.size());
        assertEquals("STU001", students.get(0).getStudentNumber());
    }

    @Test
    @DisplayName("Should find students by backlogs")
    void testFindByBacklogsEquals() {
        // Given
        studentRepository.save(testStudent);

        // When
        List<Student> students = studentRepository.findByBacklogsEquals(0);

        // Then
        assertEquals(1, students.size());
    }

    @Test
    @DisplayName("Should update existing student")
    void testUpdate() {
        // Given
        studentRepository.save(testStudent);
        testStudent.setName("Updated Name");

        // When
        Student updated = studentRepository.save(testStudent);

        // Then
        assertEquals("Updated Name", updated.getName());
        Optional<Student> found = studentRepository.findByStudentNumber("STU001");
        assertTrue(found.isPresent());
        assertEquals("Updated Name", found.get().getName());
    }
}
