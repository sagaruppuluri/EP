package com.university.studentapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.studentapi.dto.*;
import com.university.studentapi.exception.DuplicateStudentException;
import com.university.studentapi.exception.StudentNotFoundException;
import com.university.studentapi.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for StudentController
 */
@WebMvcTest(StudentController.class)
@DisplayName("StudentController Integration Tests")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    private StudentResponse studentResponse;
    private StudentCreateRequest createRequest;

    @BeforeEach
    void setUp() {
        AddressDTO address = AddressDTO.builder()
                .street("123 Main St")
                .city("Mumbai")
                .state("Maharashtra")
                .country("India")
                .build();

        createRequest = StudentCreateRequest.builder()
                .studentNumber("STU001")
                .name("John Doe")
                .address(address)
                .cgpa(8.5)
                .backlogs(0)
                .build();

        studentResponse = StudentResponse.builder()
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
    @DisplayName("GET /v1/students - Should return paginated students")
    void testGetAllStudents() throws Exception {
        // Given
        StudentPageResponse pageResponse = StudentPageResponse.builder()
                .content(Arrays.asList(studentResponse))
                .page(StudentPageResponse.PageInfo.builder()
                        .number(0)
                        .size(20)
                        .totalElements(1)
                        .totalPages(1)
                        .build())
                .build();

        when(studentService.getAllStudents(anyInt(), anyInt(), any(), any(), any(), any(), any()))
                .thenReturn(pageResponse);

        // When & Then
        mockMvc.perform(get("/v1/students")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].studentNumber").value("STU001"))
                .andExpect(jsonPath("$.page.totalElements").value(1));
    }

    @Test
    @DisplayName("GET /v1/students/{studentNumber} - Should return student")
    void testGetStudentByNumber_Success() throws Exception {
        // Given
        when(studentService.getStudentByNumber("STU001")).thenReturn(studentResponse);

        // When & Then
        mockMvc.perform(get("/v1/students/STU001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentNumber").value("STU001"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.cgpa").value(8.5));
    }

    @Test
    @DisplayName("GET /v1/students/{studentNumber} - Should return 404 when not found")
    void testGetStudentByNumber_NotFound() throws Exception {
        // Given
        when(studentService.getStudentByNumber("STU999"))
                .thenThrow(new StudentNotFoundException("STU999", true));

        // When & Then
        mockMvc.perform(get("/v1/students/STU999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    @DisplayName("POST /v1/students - Should create student successfully")
    void testCreateStudent_Success() throws Exception {
        // Given
        when(studentService.createStudent(any(StudentCreateRequest.class)))
                .thenReturn(studentResponse);

        // When & Then
        mockMvc.perform(post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.studentNumber").value("STU001"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @DisplayName("POST /v1/students - Should return 400 for invalid data")
    void testCreateStudent_ValidationFailure() throws Exception {
        // Given - Invalid CGPA
        StudentCreateRequest invalidRequest = StudentCreateRequest.builder()
                .studentNumber("STU001")
                .name("John Doe")
                .address(createRequest.getAddress())
                .cgpa(15.0) // Invalid - exceeds max
                .backlogs(0)
                .build();

        // When & Then
        mockMvc.perform(post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    @DisplayName("POST /v1/students - Should return 409 for duplicate student")
    void testCreateStudent_Duplicate() throws Exception {
        // Given
        when(studentService.createStudent(any(StudentCreateRequest.class)))
                .thenThrow(new DuplicateStudentException("STU001", true));

        // When & Then
        mockMvc.perform(post("/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Conflict"));
    }

    @Test
    @DisplayName("PUT /v1/students/{studentNumber} - Should update student")
    void testUpdateStudent_Success() throws Exception {
        // Given
        StudentUpdateRequest updateRequest = StudentUpdateRequest.builder()
                .name("Jane Doe")
                .cgpa(9.0)
                .build();

        StudentResponse updatedResponse = StudentResponse.builder()
                .studentNumber("STU001")
                .name("Jane Doe")
                .address(studentResponse.getAddress())
                .cgpa(9.0)
                .backlogs(0)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        when(studentService.updateStudent(eq("STU001"), any(StudentUpdateRequest.class)))
                .thenReturn(updatedResponse);

        // When & Then
        mockMvc.perform(put("/v1/students/STU001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.cgpa").value(9.0));
    }

    @Test
    @DisplayName("DELETE /v1/students/{studentNumber} - Should delete student")
    void testDeleteStudent_Success() throws Exception {
        // When & Then
        mockMvc.perform(delete("/v1/students/STU001"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /v1/students/statistics - Should return statistics")
    void testGetStatistics() throws Exception {
        // Given
        StudentStatistics stats = StudentStatistics.builder()
                .totalStudents(10)
                .averageCgpa(8.5)
                .studentsWithNoBacklogs(7)
                .studentsWithBacklogs(3)
                .topPerformers(2)
                .cityDistribution(Collections.singletonMap("Mumbai", 5L))
                .build();

        when(studentService.getStatistics()).thenReturn(stats);

        // When & Then
        mockMvc.perform(get("/v1/students/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalStudents").value(10))
                .andExpect(jsonPath("$.averageCgpa").value(8.5))
                .andExpect(jsonPath("$.topPerformers").value(2));
    }

    @Test
    @DisplayName("POST /v1/students/search - Should perform advanced search")
    void testAdvancedSearch() throws Exception {
        // Given
        when(studentService.advancedSearch(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(Arrays.asList(studentResponse));

        // When & Then
        mockMvc.perform(post("/v1/students/search")
                        .param("name", "John")
                        .param("minCgpa", "8.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].studentNumber").value("STU001"));
    }
}
