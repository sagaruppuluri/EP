package com.example.demo.service;

import com.example.demo.repository.Database;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Database database;
    private final EmailService emailService;

    // Constructor-based dependency injection is used here. Spring will automatically
    // inject the required dependencies (Database and EmailService) 
    // when creating an instance of UserService
    // It is optional to use @Autowired annotation on the constructor, 
    // as Spring will automatically
    // detect it and inject the dependencies. 
    // However, you can add @Autowired for clarity if you prefer.
    
    public UserService(Database database, EmailService emailService) {
        this.database = database;
        this.emailService = emailService;
    }

    public void createUser(String username) {
        database.save(username);
        emailService.sendWelcomeEmail(username);
    }
}