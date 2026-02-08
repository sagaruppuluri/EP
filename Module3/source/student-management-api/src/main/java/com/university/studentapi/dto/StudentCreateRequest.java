package com.university.studentapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for student create/update requests with validation
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {
    
    @NotBlank(message = "Student number is required")
    @Pattern(regexp = "^STU[0-9]{3,6}$", message = "Student number must match pattern STU followed by 3-6 digits")
    private String studentNumber;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @NotNull(message = "Address is required")
    @Valid
    private AddressDTO address;
    
    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA must be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA must not exceed 10.0")
    private Double cgpa;
    
    @NotNull(message = "Backlogs field is required")
    @Min(value = 0, message = "Backlogs cannot be negative")
    private Integer backlogs;
}
