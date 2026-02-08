package com.university.studentapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Test to ensure Spring Boot application context loads successfully
 */
@SpringBootTest
@DisplayName("Application Context Test")
class StudentManagementApiApplicationTests {

    @Test
    @DisplayName("Should load application context successfully")
    void contextLoads() {
        // This test passes if the application context loads without errors
    }
}
