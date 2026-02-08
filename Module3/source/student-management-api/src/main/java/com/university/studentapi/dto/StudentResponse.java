package com.university.studentapi.dto;

import com.university.studentapi.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for student response
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    
    private String studentNumber;
    private String name;
    private AddressDTO address;
    private Double cgpa;
    private Integer backlogs;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
