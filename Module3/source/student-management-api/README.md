# Student Management API

A comprehensive REST API for managing student records in an educational institution, built with Spring Boot 3 and Java 17.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [API Endpoints](#api-endpoints)
- [Architecture](#architecture)

## ✨ Features

- ✅ Complete CRUD operations for student management
- ✅ Advanced search and filtering capabilities
- ✅ Pagination support for large datasets
- ✅ Input validation with detailed error messages
- ✅ Global exception handling
- ✅ RESTful API design
- ✅ OpenAPI/Swagger documentation
- ✅ In-memory storage for demonstration
- ✅ Comprehensive unit and integration tests
- ✅ Statistics and analytics endpoints

## 🛠 Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.2
- **Build Tool**: Maven
- **Documentation**: SpringDoc OpenAPI 3 (Swagger)
- **Testing**: JUnit 5, Mockito, REST Assured
- **Validation**: Jakarta Bean Validation
- **Logging**: SLF4J with Logback

## 📁 Project Structure

```
student-management-api/
├── src/
│   ├── main/
│   │   ├── java/com/university/studentapi/
│   │   │   ├── config/                 # Configuration classes
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/             # REST Controllers
│   │   │   │   ├── StudentController.java
│   │   │   │   └── HealthController.java
│   │   │   ├── dto/                    # Data Transfer Objects
│   │   │   │   ├── StudentCreateRequest.java
│   │   │   │   ├── StudentUpdateRequest.java
│   │   │   │   ├── StudentResponse.java
│   │   │   │   ├── StudentPageResponse.java
│   │   │   │   ├── StudentStatistics.java
│   │   │   │   └── AddressDTO.java
│   │   │   ├── exception/              # Exception Handling
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── StudentNotFoundException.java
│   │   │   │   ├── DuplicateStudentException.java
│   │   │   │   └── ErrorResponse.java
│   │   │   ├── model/                  # Domain Models
│   │   │   │   └── Student.java
│   │   │   ├── repository/             # Data Access Layer
│   │   │   │   └── StudentRepository.java
│   │   │   ├── service/                # Business Logic
│   │   │   │   └── StudentService.java
│   │   │   ├── util/                   # Utilities
│   │   │   │   └── StudentMapper.java
│   │   │   └── StudentManagementApiApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/university/studentapi/
│           ├── controller/
│           │   └── StudentControllerTest.java
│           ├── service/
│           │   └── StudentServiceTest.java
│           └── repository/
│               └── StudentRepositoryTest.java
├── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation & Running

1. **Extract the project**
   ```bash
   unzip student-management-api.zip
   cd student-management-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR directly:
   ```bash
   java -jar target/student-management-api-1.0.0.jar
   ```

4. **Access the application**
   - API Base URL: `http://localhost:8080/v1`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - Health Check: `http://localhost:8080/v1/health`

## 📚 API Documentation

The API is fully documented using OpenAPI 3.0 specification. Once the application is running, you can access:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v1/api-docs

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=StudentServiceTest
```

### Test Coverage
The project includes:
- **Unit Tests**: Service and Repository layer tests
- **Integration Tests**: Controller layer tests with MockMvc
- **Test Coverage**: ~90% code coverage

## 🔌 API Endpoints

### Health Check
- `GET /v1/health` - Check API health status

### Student Management

#### Create Student
```http
POST /v1/students
Content-Type: application/json

{
  "studentNumber": "STU001",
  "name": "John Doe",
  "address": {
    "street": "123 Main Street",
    "city": "Mumbai",
    "state": "Maharashtra",
    "country": "India"
  },
  "cgpa": 8.5,
  "backlogs": 0
}
```

#### Get All Students (with pagination)
```http
GET /v1/students?page=0&size=20&name=John&minCgpa=7.5&city=Mumbai&sortBy=cgpa&sortOrder=desc
```

#### Get Student by Number
```http
GET /v1/students/STU001
```

#### Update Student
```http
PUT /v1/students/STU001
Content-Type: application/json

{
  "name": "Jane Doe",
  "cgpa": 9.0
}
```

#### Partial Update
```http
PATCH /v1/students/STU001
Content-Type: application/json

{
  "cgpa": 9.5
}
```

#### Delete Student
```http
DELETE /v1/students/STU001
```

#### Advanced Search
```http
POST /v1/students/search?name=John&city=Mumbai&minCgpa=8.0&maxCgpa=10.0&maxBacklogs=2
```

#### Get Statistics
```http
GET /v1/students/statistics
```

### Query Parameters

| Parameter | Type | Description | Default |
|-----------|------|-------------|---------|
| page | integer | Page number (0-based) | 0 |
| size | integer | Items per page (1-100) | 20 |
| name | string | Filter by name (partial match) | - |
| minCgpa | double | Minimum CGPA (0-10) | - |
| city | string | Filter by city | - |
| sortBy | string | Sort field (studentNumber, name, cgpa, createdDate) | studentNumber |
| sortOrder | string | Sort order (asc, desc) | asc |

### Response Examples

#### Success Response (200 OK)
```json
{
  "studentNumber": "STU001",
  "name": "John Doe",
  "address": {
    "street": "123 Main Street",
    "city": "Mumbai",
    "state": "Maharashtra",
    "country": "India"
  },
  "cgpa": 8.5,
  "backlogs": 0,
  "createdDate": "2024-02-06T10:30:00",
  "lastModifiedDate": "2024-02-06T10:30:00"
}
```

#### Error Response (400 Bad Request)
```json
{
  "timestamp": "2024-02-06T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/v1/students",
  "errors": [
    {
      "field": "cgpa",
      "message": "must be between 0.0 and 10.0",
      "rejectedValue": "12.5"
    }
  ]
}
```

#### Paginated Response
```json
{
  "content": [
    {
      "studentNumber": "STU001",
      "name": "John Doe",
      ...
    }
  ],
  "page": {
    "number": 0,
    "size": 20,
    "totalElements": 150,
    "totalPages": 8
  }
}
```

## 🏗 Architecture

The application follows Spring Boot 3 layered architecture:

### Layer Description

1. **Controller Layer** (`@RestController`)
   - Handles HTTP requests and responses
   - Input validation
   - URI mapping

2. **Service Layer** (`@Service`)
   - Business logic implementation
   - Transaction management
   - Orchestration between layers

3. **Repository Layer** (`@Repository`)
   - Data access logic
   - In-memory storage operations
   - CRUD operations

4. **DTO Layer**
   - Data transfer objects
   - Request/Response models
   - Validation annotations

5. **Exception Layer**
   - Global exception handling
   - Custom exceptions
   - Standardized error responses

### Key Design Patterns

- **Dependency Injection**: Constructor-based DI for loose coupling
- **DTO Pattern**: Separation of domain models and API contracts
- **Repository Pattern**: Abstraction of data access
- **Builder Pattern**: Lombok builders for object creation
- **Exception Handler Pattern**: Centralized exception handling

### Validation Rules

- **studentNumber**: Pattern `^STU[0-9]{3,6}$` (e.g., STU001)
- **name**: 2-100 characters, required
- **cgpa**: 0.0-10.0, required
- **backlogs**: >= 0, required
- **address**: All fields required (street, city, state, country)

## 📊 Statistics Endpoint

The `/v1/students/statistics` endpoint provides:

- Total number of students
- Average CGPA
- Students with no backlogs
- Students with backlogs
- Top performers (CGPA >= 9.0)
- City-wise distribution

Example Response:
```json
{
  "totalStudents": 150,
  "averageCgpa": 7.85,
  "studentsWithNoBacklogs": 120,
  "studentsWithBacklogs": 30,
  "topPerformers": 15,
  "cityDistribution": {
    "Mumbai": 45,
    "Delhi": 35,
    "Bangalore": 30,
    "Chennai": 25,
    "Pune": 15
  }
}
```

## 🔧 Configuration

The application can be configured via `src/main/resources/application.yml`:

```yaml
server:
  port: 8080  # Change server port

logging:
  level:
    com.university.studentapi: DEBUG  # Adjust logging level

springdoc:
  swagger-ui:
    path: /swagger-ui.html  # Customize Swagger UI path
```

## 🤝 Contributing

This is a demonstration project. For production use, consider:

1. Replacing in-memory storage with a database (H2, PostgreSQL, MySQL)
2. Adding Spring Security for authentication
3. Implementing caching (Redis, Caffeine)
4. Adding API rate limiting
5. Implementing audit logging
6. Adding monitoring (Actuator, Prometheus)

## 📝 License

This project is licensed under the MIT License.

## 👥 Contact

For support or questions, please contact:
- Email: support@university.edu
- Website: https://university.edu/support

---

**Note**: This is a demonstration project using in-memory storage. Data is not persisted and will be lost when the application restarts.
