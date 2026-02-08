package com.university.studentapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Student entity representing a student record.
 * Uses in-memory storage (no JPA annotations needed).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    private String studentNumber;
    private String name;
    private Address address;
    private Double cgpa;
    private Integer backlogs;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    /**
     * Address embedded object
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String country;
    }
}
