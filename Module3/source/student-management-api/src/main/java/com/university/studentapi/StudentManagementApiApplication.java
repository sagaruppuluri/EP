package com.university.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Student Management API application.
 * 
 * @SpringBootApplication combines:
 * - @Configuration: Marks as configuration source
 * - @EnableAutoConfiguration: Enables auto-configuration
 * - @ComponentScan: Scans for components in current package and sub-packages
 */
@SpringBootApplication
public class StudentManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApiApplication.class, args);
    }
}
