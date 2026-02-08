package com.university.studentapi.config;

import com.university.studentapi.model.Student;
import com.university.studentapi.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

/**
 * Data loader to populate initial sample data
 * Only runs when 'demo' profile is active
 */
@Configuration
@Slf4j
public class DataLoader {

    @Bean
    @Profile("demo")
    public CommandLineRunner loadSampleData(StudentRepository repository) {
        return args -> {
            log.info("Loading sample student data...");

            // Student 1
            Student student1 = Student.builder()
                    .studentNumber("STU001")
                    .name("Rajesh Kumar")
                    .address(Student.Address.builder()
                            .street("15 MG Road")
                            .city("Bangalore")
                            .state("Karnataka")
                            .country("India")
                            .build())
                    .cgpa(8.7)
                    .backlogs(0)
                    .createdDate(LocalDateTime.now().minusDays(30))
                    .lastModifiedDate(LocalDateTime.now().minusDays(30))
                    .build();

            // Student 2
            Student student2 = Student.builder()
                    .studentNumber("STU002")
                    .name("Priya Sharma")
                    .address(Student.Address.builder()
                            .street("45 Marine Drive")
                            .city("Mumbai")
                            .state("Maharashtra")
                            .country("India")
                            .build())
                    .cgpa(9.5)
                    .backlogs(0)
                    .createdDate(LocalDateTime.now().minusDays(25))
                    .lastModifiedDate(LocalDateTime.now().minusDays(25))
                    .build();

            // Student 3
            Student student3 = Student.builder()
                    .studentNumber("STU003")
                    .name("Amit Patel")
                    .address(Student.Address.builder()
                            .street("22 Linking Road")
                            .city("Mumbai")
                            .state("Maharashtra")
                            .country("India")
                            .build())
                    .cgpa(8.8)
                    .backlogs(1)
                    .createdDate(LocalDateTime.now().minusDays(20))
                    .lastModifiedDate(LocalDateTime.now().minusDays(20))
                    .build();

            // Student 4
            Student student4 = Student.builder()
                    .studentNumber("STU004")
                    .name("Sneha Reddy")
                    .address(Student.Address.builder()
                            .street("88 Park Street")
                            .city("Hyderabad")
                            .state("Telangana")
                            .country("India")
                            .build())
                    .cgpa(7.9)
                    .backlogs(2)
                    .createdDate(LocalDateTime.now().minusDays(15))
                    .lastModifiedDate(LocalDateTime.now().minusDays(15))
                    .build();

            // Student 5
            Student student5 = Student.builder()
                    .studentNumber("STU005")
                    .name("Vikram Singh")
                    .address(Student.Address.builder()
                            .street("12 Connaught Place")
                            .city("Delhi")
                            .state("Delhi")
                            .country("India")
                            .build())
                    .cgpa(9.2)
                    .backlogs(0)
                    .createdDate(LocalDateTime.now().minusDays(10))
                    .lastModifiedDate(LocalDateTime.now().minusDays(10))
                    .build();

            // Student 6
            Student student6 = Student.builder()
                    .studentNumber("STU006")
                    .name("Anjali Desai")
                    .address(Student.Address.builder()
                            .street("56 FC Road")
                            .city("Pune")
                            .state("Maharashtra")
                            .country("India")
                            .build())
                    .cgpa(8.3)
                    .backlogs(0)
                    .createdDate(LocalDateTime.now().minusDays(8))
                    .lastModifiedDate(LocalDateTime.now().minusDays(8))
                    .build();

            // Student 7
            Student student7 = Student.builder()
                    .studentNumber("STU007")
                    .name("Karthik Menon")
                    .address(Student.Address.builder()
                            .street("34 Anna Salai")
                            .city("Chennai")
                            .state("Tamil Nadu")
                            .country("India")
                            .build())
                    .cgpa(7.5)
                    .backlogs(3)
                    .createdDate(LocalDateTime.now().minusDays(5))
                    .lastModifiedDate(LocalDateTime.now().minusDays(5))
                    .build();

            // Student 8
            Student student8 = Student.builder()
                    .studentNumber("STU008")
                    .name("Divya Iyer")
                    .address(Student.Address.builder()
                            .street("78 Brigade Road")
                            .city("Bangalore")
                            .state("Karnataka")
                            .country("India")
                            .build())
                    .cgpa(9.0)
                    .backlogs(0)
                    .createdDate(LocalDateTime.now().minusDays(3))
                    .lastModifiedDate(LocalDateTime.now().minusDays(3))
                    .build();

            // Student 9
            Student student9 = Student.builder()
                    .studentNumber("STU009")
                    .name("Rohan Gupta")
                    .address(Student.Address.builder()
                            .street("90 Residency Road")
                            .city("Bangalore")
                            .state("Karnataka")
                            .country("India")
                            .build())
                    .cgpa(8.1)
                    .backlogs(1)
                    .createdDate(LocalDateTime.now().minusDays(2))
                    .lastModifiedDate(LocalDateTime.now().minusDays(2))
                    .build();

            // Student 10
            Student student10 = Student.builder()
                    .studentNumber("STU010")
                    .name("Meera Krishnan")
                    .address(Student.Address.builder()
                            .street("23 MG Road")
                            .city("Kochi")
                            .state("Kerala")
                            .country("India")
                            .build())
                    .cgpa(8.9)
                    .backlogs(0)
                    .createdDate(LocalDateTime.now().minusDays(1))
                    .lastModifiedDate(LocalDateTime.now().minusDays(1))
                    .build();

            // Save all students
            repository.save(student1);
            repository.save(student2);
            repository.save(student3);
            repository.save(student4);
            repository.save(student5);
            repository.save(student6);
            repository.save(student7);
            repository.save(student8);
            repository.save(student9);
            repository.save(student10);

            log.info("Sample data loaded successfully. Total students: {}", repository.count());
        };
    }
}
