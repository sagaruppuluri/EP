package com.university.studentapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI/Swagger configuration
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI studentManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .description("""
                                Comprehensive REST API for managing student records in an educational institution.
                                
                                Features:
                                - Complete CRUD operations
                                - Advanced search and filtering
                                - Pagination support
                                - Input validation
                                - Error handling
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("API Support Team")
                                .email("support@university.edu")
                                .url("https://university.edu/support"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080/v1")
                                .description("Development server"),
                        new Server()
                                .url("https://api-staging.university.edu/v1")
                                .description("Staging server"),
                        new Server()
                                .url("https://api.university.edu/v1")
                                .description("Production server")
                ));
    }
}
