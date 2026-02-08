package com.university.studentapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * DTO for student statistics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentStatistics {
    
    private long totalStudents;
    private double averageCgpa;
    private long studentsWithNoBacklogs;
    private long studentsWithBacklogs;
    private long topPerformers; // CGPA >= 9.0
    private Map<String, Long> cityDistribution;
}
