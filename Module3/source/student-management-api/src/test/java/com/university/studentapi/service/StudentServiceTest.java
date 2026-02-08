package com.university.studentapi.service;

import com.university.studentapi.dto.*;
import com.university.studentapi.exception.DuplicateStudentException;
import com.university.studentapi.exception.StudentNotFoundException;
import com.university.studentapi.model.Student;
import com.university.studentapi.repository.StudentRepository;
import com.university.studentapi.util.StudentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for StudentService
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("StudentService Tests")
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    private Student testStudent;
    private StudentCreateRequest createRequest;
    private StudentResponse studentResponse;

    @BeforeEach
    void setUp() {
        // Setup test data
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

        AddressDTO addressDTO = AddressDTO.builder()
                .street("123 Main St")
                .city("Mumbai")
                .state("Maharashtra")
                .country("India")
                .build();

        createRequest = StudentCreateRequest.builder()
                .studentNumber("STU001")
                .name("John Doe")
                .address(addressDTO)
                .cgpa(8.5)
                .backlogs(0)
                .build();

        studentResponse = StudentResponse.builder()
                .studentNumber("STU001")
                .name("John Doe")
                .address(addressDTO)
                .cgpa(8.5)
                .backlogs(0)
                .createdDate(testStudent.getCreatedDate())
                .lastModifiedDate(testStudent.getLastModifiedDate())
                .build();
    }

    @Test
    @DisplayName("Should create student successfully")
    void testCreateStudent_Success() {
        // Given
        when(studentRepository.existsByStudentNumber(createRequest.getStudentNumber())).thenReturn(false);
        when(studentMapper.toEntity(createRequest)).thenReturn(testStudent);
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);
        when(studentMapper.toResponse(testStudent)).thenReturn(studentResponse);

        // When
        StudentResponse result = studentService.createStudent(createRequest);

        // Then
        assertNotNull(result);
        assertEquals("STU001", result.getStudentNumber());
        assertEquals("John Doe", result.getName());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    @DisplayName("Should throw DuplicateStudentException when student already exists")
    void testCreateStudent_DuplicateStudent() {
        // Given
        when(studentRepository.existsByStudentNumber(createRequest.getStudentNumber())).thenReturn(true);

        // When & Then
        assertThrows(DuplicateStudentException.class, () -> {
            studentService.createStudent(createRequest);
        });

        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("Should get student by number successfully")
    void testGetStudentByNumber_Success() {
        // Given
        when(studentRepository.findByStudentNumber("STU001")).thenReturn(Optional.of(testStudent));
        when(studentMapper.toResponse(testStudent)).thenReturn(studentResponse);

        // When
        StudentResponse result = studentService.getStudentByNumber("STU001");

        // Then
        assertNotNull(result);
        assertEquals("STU001", result.getStudentNumber());
        verify(studentRepository).findByStudentNumber("STU001");
    }

    @Test
    @DisplayName("Should throw StudentNotFoundException when student not found")
    void testGetStudentByNumber_NotFound() {
        // Given
        when(studentRepository.findByStudentNumber("STU999")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentByNumber("STU999");
        });
    }

    @Test
    @DisplayName("Should get all students with pagination")
    void testGetAllStudents() {
        // Given
        List<Student> students = Arrays.asList(testStudent);
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toResponse(any(Student.class))).thenReturn(studentResponse);

        // When
        StudentPageResponse result = studentService.getAllStudents(0, 20, null, null, null, "studentNumber", "asc");

        // Then
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(0, result.getPage().getNumber());
        assertEquals(20, result.getPage().getSize());
        assertEquals(1, result.getPage().getTotalElements());
    }

    @Test
    @DisplayName("Should filter students by name")
    void testGetAllStudents_FilterByName() {
        // Given
        List<Student> students = Arrays.asList(testStudent);
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toResponse(any(Student.class))).thenReturn(studentResponse);

        // When
        StudentPageResponse result = studentService.getAllStudents(0, 20, "John", null, null, "studentNumber", "asc");

        // Then
        assertNotNull(result);
        assertEquals(1, result.getContent().size());
    }

    @Test
    @DisplayName("Should update student successfully")
    void testUpdateStudent_Success() {
        // Given
        StudentUpdateRequest updateRequest = StudentUpdateRequest.builder()
                .name("Jane Doe")
                .cgpa(9.0)
                .build();

        when(studentRepository.findByStudentNumber("STU001")).thenReturn(Optional.of(testStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(testStudent);
        when(studentMapper.toResponse(testStudent)).thenReturn(studentResponse);

        // When
        StudentResponse result = studentService.updateStudent("STU001", updateRequest);

        // Then
        assertNotNull(result);
        verify(studentMapper).updateEntity(updateRequest, testStudent);
        verify(studentRepository).save(testStudent);
    }

    @Test
    @DisplayName("Should delete student successfully")
    void testDeleteStudent_Success() {
        // Given
        when(studentRepository.existsByStudentNumber("STU001")).thenReturn(true);

        // When
        studentService.deleteStudent("STU001");

        // Then
        verify(studentRepository).deleteByStudentNumber("STU001");
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent student")
    void testDeleteStudent_NotFound() {
        // Given
        when(studentRepository.existsByStudentNumber("STU999")).thenReturn(false);

        // When & Then
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.deleteStudent("STU999");
        });

        verify(studentRepository, never()).deleteByStudentNumber(anyString());
    }

    @Test
    @DisplayName("Should calculate statistics correctly")
    void testGetStatistics() {
        // Given
        Student student2 = Student.builder()
                .studentNumber("STU002")
                .name("Jane Smith")
                .address(testStudent.getAddress())
                .cgpa(9.5)
                .backlogs(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        when(studentRepository.findAll()).thenReturn(Arrays.asList(testStudent, student2));

        // When
        StudentStatistics stats = studentService.getStatistics();

        // Then
        assertNotNull(stats);
        assertEquals(2, stats.getTotalStudents());
        assertEquals(9.0, stats.getAverageCgpa(), 0.1);
        assertEquals(1, stats.getStudentsWithNoBacklogs());
        assertEquals(1, stats.getStudentsWithBacklogs());
        assertEquals(1, stats.getTopPerformers());
    }

    @Test
    @DisplayName("Should handle empty statistics")
    void testGetStatistics_EmptyRepository() {
        // Given
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        StudentStatistics stats = studentService.getStatistics();

        // Then
        assertNotNull(stats);
        assertEquals(0, stats.getTotalStudents());
        assertEquals(0.0, stats.getAverageCgpa());
    }
}
