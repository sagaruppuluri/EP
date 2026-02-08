package com.university.studentapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Health check controller
 */
@RestController
@RequestMapping("/v1/health")
@Tag(name = "Health", description = "API health check endpoints")
public class HealthController {

    @GetMapping
    @Operation(summary = "Health check endpoint", description = "Check if the API is running")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "timestamp", LocalDateTime.now()
        ));
    }
}
