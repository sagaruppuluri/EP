package com.example.demo.service;

import org.springframework.stereotype.Service;

// Service is a stereotype annotation that marks a class as a service component in Spring.
// You don't need to create a bean for this class in AppConfig, 
// as Spring will automatically detect it and register it 
// in the application context due to the @Service annotation.

@Service
public class EmailService {
    public void sendWelcomeEmail(String username) {
        System.out.println("Sending welcome email to: " + username);
    }
}
