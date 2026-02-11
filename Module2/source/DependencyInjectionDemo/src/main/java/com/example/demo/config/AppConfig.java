package com.example.demo.config;

import com.example.demo.repository.Database;
import com.example.demo.repository.MySQLDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration is a stereotype annotation that indicates that the class can be used
// by the Spring IoC container as a source of bean definitions.
// In this class, we define beans that will be managed by the Spring container.
// The @Bean annotation tells Spring that a method annotated with @Bean will return an object 
// that should be registered as a bean in the Spring application context.

@Configuration
public class AppConfig {

    // Define a bean for the Database interface, which will 
    // be injected into other components
    // This is required when @Component annotations are not 
    // used to automatically detect and register beans in the Spring context

    @Bean
    public Database database() {
        return new MySQLDatabase();
    }
}
