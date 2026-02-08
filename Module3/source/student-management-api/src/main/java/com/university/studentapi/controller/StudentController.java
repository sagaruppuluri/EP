package com.university.studentapi.controller;

import com.university.studentapi.dto.*;
import com.university.studentapi.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * REST Controller for Student Management API
 */
@RestController
@RequestMapping("/v1/students")
@Validated
@Slf4j
@Tag(name = "Students", description = "Student management operations")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * GET /v1/students - Get all students with pagination and filtering
     */
    @GetMapping
    @Operation(summary = "Get all students", description = "Retrieve a paginated list of all students with optional filtering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response with paginated student list"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid parameters")
    })
    public ResponseEntity<StudentPageResponse> getAllStudents(
            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") @Min(0) int page,
            
            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size,
            
            @Parameter(description = "Filter by student name (partial match)")
            @RequestParam(required = false) String name,
            
            @Parameter(description = "Filter by minimum CGPA")
            @RequestParam(required = false) @Min(0) @Max(10) Double minCgpa,
            
            @Parameter(description = "Filter by city")
            @RequestParam(required = false) String city,
            
            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "studentNumber") String sortBy,
            
            @Parameter(description = "Sort order (asc/desc)")
            @RequestParam(defaultValue = "asc") String sortOrder) {

        StudentPageResponse response = studentService.getAllStudents(
                page, size, name, minCgpa, city, sortBy, sortOrder);

        return ResponseEntity.ok(response);
    }

    /**
     * GET /v1/students/{studentNumber} - Get student by student number
     */
    @GetMapping("/{studentNumber}")
    @Operation(summary = "Get student by student number", description = "Retrieve a single student record by their unique student number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentResponse> getStudentByNumber(
            @Parameter(description = "Unique student identifier", required = true)
            @PathVariable String studentNumber) {

        StudentResponse response = studentService.getStudentByNumber(studentNumber);
        return ResponseEntity.ok(response);
    }

    /**
     * POST /v1/students - Create new student
     */
    @PostMapping
    @Operation(summary = "Create a new student", description = "Create a new student record in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - Validation failed"),
            @ApiResponse(responseCode = "409", description = "Conflict - Student already exists")
    })
    public ResponseEntity<StudentResponse> createStudent(
            @Parameter(description = "Student object to be created", required = true)
            @Valid @RequestBody StudentCreateRequest request) {

        StudentResponse response = studentService.createStudent(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{studentNumber}")
                .buildAndExpand(response.getStudentNumber())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    /**
     * PUT /v1/students/{studentNumber} - Update student (full update)
     */
    @PutMapping("/{studentNumber}")
    @Operation(summary = "Update student", description = "Update an existing student record (full update)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - Validation failed"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentResponse> updateStudent(
            @Parameter(description = "Student number", required = true)
            @PathVariable String studentNumber,
            
            @Parameter(description = "Updated student data", required = true)
            @Valid @RequestBody StudentUpdateRequest request) {

        StudentResponse response = studentService.updateStudent(studentNumber, request);
        return ResponseEntity.ok(response);
    }

    /**
     * PATCH /v1/students/{studentNumber} - Partial update student
     */
    @PatchMapping("/{studentNumber}")
    @Operation(summary = "Partial update student", description = "Partially update an existing student record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - Validation failed"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    public ResponseEntity<StudentResponse> partialUpdateStudent(
            @Parameter(description = "Student number", required = true)
            @PathVariable String studentNumber,
            
            @Parameter(description = "Partial student data to update", required = true)
            @Valid @RequestBody StudentUpdateRequest request) {

        StudentResponse response = studentService.partialUpdateStudent(studentNumber, request);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /v1/students/{studentNumber} - Delete student
     */
    @DeleteMapping("/{studentNumber}")
    @Operation(summary = "Delete student", description = "Delete a student record from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "Student number", required = true)
            @PathVariable String studentNumber) {

        studentService.deleteStudent(studentNumber);
        return ResponseEntity.noContent().build();
    }

    /**
     * POST /v1/students/search - Advanced search
     */
    @PostMapping("/search")
    @Operation(summary = "Advanced student search", description = "Search students with multiple criteria")
    @ApiResponse(responseCode = "200", description = "Search results returned")
    public ResponseEntity<List<StudentResponse>> advancedSearch(
            @Parameter(description = "Search criteria")
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) @Min(0) @Max(10) Double minCgpa,
            @RequestParam(required = false) @Min(0) @Max(10) Double maxCgpa,
            @RequestParam(required = false) @Min(0) Integer maxBacklogs) {

        List<StudentResponse> results = studentService.advancedSearch(
                name, city, state, country, minCgpa, maxCgpa, maxBacklogs);

        return ResponseEntity.ok(results);
    }

    /**
     * GET /v1/students/statistics - Get student statistics
     */
    @GetMapping("/statistics")
    @Operation(summary = "Get student statistics", description = "Retrieve statistical information about students")
    @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully")
    public ResponseEntity<StudentStatistics> getStatistics() {
        StudentStatistics statistics = studentService.getStatistics();
        return ResponseEntity.ok(statistics);
    }
}
