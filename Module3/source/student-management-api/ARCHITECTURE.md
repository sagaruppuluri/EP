# Architecture Documentation

## Overview

The Student Management API follows Spring Boot 3's layered architecture pattern, implementing clean separation of concerns and dependency injection principles.

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    HTTP Client (Browser/Postman)             │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                    Embedded Tomcat Server                    │
│                        (Port 8080)                           │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                     DispatcherServlet                        │
│                    (Front Controller)                        │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│              @RestController Layer                           │
│  ┌────────────────────┬─────────────────────────┐          │
│  │ StudentController   │   HealthController      │          │
│  │ - CRUD Operations  │   - Health Check        │          │
│  │ - Search           │                         │          │
│  │ - Statistics       │                         │          │
│  └────────────────────┴─────────────────────────┘          │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│               @Service Layer (Business Logic)                │
│  ┌──────────────────────────────────────────────┐           │
│  │          StudentService                      │           │
│  │  - Business Validations                      │           │
│  │  - Data Transformation                       │           │
│  │  - Orchestration Logic                       │           │
│  │  - Statistics Calculation                    │           │
│  └──────────────────────────────────────────────┘           │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│           @Repository Layer (Data Access)                    │
│  ┌──────────────────────────────────────────────┐           │
│  │       StudentRepository                      │           │
│  │  - CRUD Operations                           │           │
│  │  - Query Methods                             │           │
│  │  - In-Memory Storage                         │           │
│  └──────────────────────────────────────────────┘           │
└─────────────────────────────────────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────────┐
│              In-Memory Data Storage                          │
│           (ConcurrentHashMap<String, Student>)               │
└─────────────────────────────────────────────────────────────┘
```

## Component Details

### 1. Controller Layer

**Responsibilities:**
- Handle HTTP requests and responses
- Input validation using Bean Validation
- URI mapping with @RequestMapping
- Response status codes
- Exception handling delegation

**Key Classes:**
- `StudentController`: Main REST endpoints for student operations
- `HealthController`: Application health check

**Annotations Used:**
- `@RestController`
- `@RequestMapping`
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`
- `@Valid`, `@Validated`
- `@PathVariable`, `@RequestParam`, `@RequestBody`

### 2. Service Layer

**Responsibilities:**
- Business logic implementation
- Data transformation between DTOs and entities
- Orchestration of multiple operations
- Business validations
- Statistics calculations

**Key Classes:**
- `StudentService`: Core business logic for student management

**Annotations Used:**
- `@Service`
- `@Slf4j` (Logging)

**Key Methods:**
- `createStudent()`: Create new student with validations
- `updateStudent()`: Full update of student record
- `partialUpdateStudent()`: Partial update (PATCH)
- `deleteStudent()`: Delete student
- `getAllStudents()`: Retrieve with pagination and filtering
- `getStatistics()`: Calculate and return statistics

### 3. Repository Layer

**Responsibilities:**
- Data access operations
- CRUD operations
- Query execution
- In-memory storage management

**Key Classes:**
- `StudentRepository`: Data access for student entities

**Annotations Used:**
- `@Repository`

**Storage:**
- `ConcurrentHashMap<String, Student>` for thread-safe operations

### 4. Model Layer

**Entities:**
- `Student`: Core domain model
- `Student.Address`: Embedded address object

**Annotations Used:**
- `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` (Lombok)

### 5. DTO Layer

**Purpose:** Decouple API contracts from domain models

**Key DTOs:**
- `StudentCreateRequest`: Request for creating students
- `StudentUpdateRequest`: Request for updating students
- `StudentResponse`: Response containing student data
- `StudentPageResponse`: Paginated response wrapper
- `StudentStatistics`: Statistics response
- `AddressDTO`: Address data transfer

**Validation Annotations:**
- `@NotNull`, `@NotBlank`
- `@Min`, `@Max`
- `@DecimalMin`, `@DecimalMax`
- `@Size`
- `@Pattern`
- `@Valid`

### 6. Exception Handling

**Global Exception Handler:**
- `@RestControllerAdvice` for centralized exception handling
- Custom exceptions: `StudentNotFoundException`, `DuplicateStudentException`
- Standardized error responses via `ErrorResponse`

**HTTP Status Codes:**
- 200 OK: Successful GET/PUT/PATCH
- 201 Created: Successful POST
- 204 No Content: Successful DELETE
- 400 Bad Request: Validation errors
- 404 Not Found: Resource not found
- 409 Conflict: Duplicate resource
- 500 Internal Server Error: Unexpected errors

### 7. Configuration

**Key Configurations:**
- `OpenApiConfig`: Swagger/OpenAPI documentation
- `DataLoader`: Sample data loader (demo profile)
- `application.yml`: Application configuration

## Request Flow

### Example: Create Student Request

```
1. HTTP POST /v1/students
   │
2. DispatcherServlet receives request
   │
3. Maps to StudentController.createStudent()
   │
4. @Valid annotation triggers validation
   │
5. Controller calls studentService.createStudent()
   │
6. Service validates business rules
   │
7. Service maps DTO to Entity using StudentMapper
   │
8. Service calls studentRepository.save()
   │
9. Repository stores in ConcurrentHashMap
   │
10. Repository returns saved entity
    │
11. Service maps Entity to Response DTO
    │
12. Service returns DTO to Controller
    │
13. Controller returns ResponseEntity<StudentResponse>
    │
14. Jackson serializes DTO to JSON
    │
15. HTTP 201 Created response sent to client
```

## Dependency Injection

### Constructor Injection (Recommended)

```java
@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper mapper;
    
    // Constructor injection - implicit @Autowired
    public StudentService(StudentRepository repository, 
                         StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
```

**Benefits:**
- Immutable dependencies (final fields)
- Easy to test (can pass mocks)
- Makes required dependencies explicit
- No need for @Autowired annotation

## Design Patterns Used

### 1. Layered Architecture Pattern
Separation into Controller → Service → Repository layers

### 2. Repository Pattern
Abstraction of data access logic

### 3. DTO Pattern
Separation of API contracts from domain models

### 4. Builder Pattern
Using Lombok's `@Builder` for object creation

### 5. Singleton Pattern
Spring beans are singletons by default

### 6. Dependency Injection Pattern
Inversion of Control via Spring's IoC container

### 7. Exception Handler Pattern
Centralized exception handling with @RestControllerAdvice

## Threading and Concurrency

- Uses `ConcurrentHashMap` for thread-safe in-memory storage
- Spring controllers are thread-safe (singleton scope)
- No shared mutable state in services
- Stateless design for scalability

## Validation Strategy

### Input Validation (Controller Layer)
- Bean Validation annotations on DTOs
- `@Valid` annotation triggers validation
- Validation errors caught by `GlobalExceptionHandler`

### Business Validation (Service Layer)
- Duplicate checks
- Business rule enforcement
- Custom validation logic

## Testing Strategy

### Unit Tests
- **Service Layer**: Mock repository and mapper
- **Repository Layer**: Test in-memory operations
- Uses JUnit 5 and Mockito

### Integration Tests
- **Controller Layer**: MockMvc for HTTP testing
- Tests complete request/response cycle
- Validates JSON serialization/deserialization

## Performance Considerations

### Current Implementation
- In-memory storage for fast access
- No database overhead
- ConcurrentHashMap for thread-safety

### Future Enhancements
- Add caching layer (Redis, Caffeine)
- Database connection pooling
- Async processing for heavy operations
- Rate limiting

## Security Considerations

### Current Implementation
- Input validation prevents injection attacks
- No authentication/authorization (demo)

### Production Recommendations
- Add Spring Security
- Implement JWT-based authentication
- HTTPS/TLS encryption
- Rate limiting
- CORS configuration
- SQL injection prevention (if using database)

## Monitoring and Observability

### Actuator Endpoints
- `/actuator/health`: Application health
- `/actuator/info`: Application info
- `/actuator/metrics`: Performance metrics

### Logging
- SLF4J with Logback
- Configurable log levels
- Request/Response logging in controllers
- Business event logging in services

## Scalability

### Horizontal Scaling
- Stateless design allows multiple instances
- In-memory storage limitation (not shared)
- Solution: Replace with distributed cache or database

### Vertical Scaling
- JVM heap size configuration
- Connection pool sizing (future)
- Thread pool tuning (Tomcat)

## API Versioning

- URL-based versioning: `/v1/students`
- Future versions: `/v2/students`
- Maintains backward compatibility

## Documentation

### API Documentation
- OpenAPI 3.0 specification
- Swagger UI for interactive testing
- Comprehensive endpoint descriptions

### Code Documentation
- JavaDoc comments on public methods
- Inline comments for complex logic
- README with usage examples

## Future Enhancements

1. **Persistence**
   - Replace in-memory storage with JPA/Hibernate
   - H2/PostgreSQL/MySQL integration

2. **Security**
   - Spring Security integration
   - JWT authentication
   - Role-based access control

3. **Caching**
   - Spring Cache abstraction
   - Redis integration

4. **Async Processing**
   - @Async for heavy operations
   - Message queues (RabbitMQ, Kafka)

5. **Monitoring**
   - Prometheus metrics
   - Grafana dashboards
   - Distributed tracing

6. **CI/CD**
   - Docker containerization
   - Kubernetes deployment
   - GitHub Actions pipeline
