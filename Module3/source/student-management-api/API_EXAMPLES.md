# API Usage Examples

This document provides practical examples for using the Student Management API.

## Table of Contents
1. [Basic CRUD Operations](#basic-crud-operations)
2. [Search and Filter](#search-and-filter)
3. [Pagination](#pagination)
4. [Statistics](#statistics)
5. [Error Handling](#error-handling)

## Basic CRUD Operations

### 1. Create a Student

**Request:**
```bash
curl -X POST http://localhost:8080/v1/students \
  -H "Content-Type: application/json" \
  -d '{
    "studentNumber": "STU001",
    "name": "Rajesh Kumar",
    "address": {
      "street": "15 MG Road",
      "city": "Bangalore",
      "state": "Karnataka",
      "country": "India"
    },
    "cgpa": 8.7,
    "backlogs": 0
  }'
```

**Response (201 Created):**
```json
{
  "studentNumber": "STU001",
  "name": "Rajesh Kumar",
  "address": {
    "street": "15 MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "country": "India"
  },
  "cgpa": 8.7,
  "backlogs": 0,
  "createdDate": "2024-02-06T10:30:00",
  "lastModifiedDate": "2024-02-06T10:30:00"
}
```

### 2. Get a Student by Number

**Request:**
```bash
curl http://localhost:8080/v1/students/STU001
```

**Response (200 OK):**
```json
{
  "studentNumber": "STU001",
  "name": "Rajesh Kumar",
  "address": {
    "street": "15 MG Road",
    "city": "Bangalore",
    "state": "Karnataka",
    "country": "India"
  },
  "cgpa": 8.7,
  "backlogs": 0,
  "createdDate": "2024-02-06T10:30:00",
  "lastModifiedDate": "2024-02-06T10:30:00"
}
```

### 3. Update a Student (Full Update)

**Request:**
```bash
curl -X PUT http://localhost:8080/v1/students/STU001 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Rajesh Kumar",
    "address": {
      "street": "20 MG Road",
      "city": "Bangalore",
      "state": "Karnataka",
      "country": "India"
    },
    "cgpa": 9.0,
    "backlogs": 0
  }'
```

### 4. Partial Update (PATCH)

**Request:**
```bash
curl -X PATCH http://localhost:8080/v1/students/STU001 \
  -H "Content-Type: application/json" \
  -d '{
    "cgpa": 9.2
  }'
```

### 5. Delete a Student

**Request:**
```bash
curl -X DELETE http://localhost:8080/v1/students/STU001
```

**Response (204 No Content)**

## Search and Filter

### 1. Get All Students (Basic)

**Request:**
```bash
curl "http://localhost:8080/v1/students"
```

### 2. Filter by Name

**Request:**
```bash
curl "http://localhost:8080/v1/students?name=Rajesh"
```

### 3. Filter by Minimum CGPA

**Request:**
```bash
curl "http://localhost:8080/v1/students?minCgpa=8.5"
```

### 4. Filter by City

**Request:**
```bash
curl "http://localhost:8080/v1/students?city=Bangalore"
```

### 5. Combined Filters with Sorting

**Request:**
```bash
curl "http://localhost:8080/v1/students?city=Mumbai&minCgpa=8.0&sortBy=cgpa&sortOrder=desc"
```

**Response:**
```json
{
  "content": [
    {
      "studentNumber": "STU005",
      "name": "Priya Sharma",
      "address": {
        "street": "45 Marine Drive",
        "city": "Mumbai",
        "state": "Maharashtra",
        "country": "India"
      },
      "cgpa": 9.5,
      "backlogs": 0,
      "createdDate": "2024-02-05T10:30:00",
      "lastModifiedDate": "2024-02-05T10:30:00"
    },
    {
      "studentNumber": "STU003",
      "name": "Amit Patel",
      "address": {
        "street": "22 Linking Road",
        "city": "Mumbai",
        "state": "Maharashtra",
        "country": "India"
      },
      "cgpa": 8.8,
      "backlogs": 1,
      "createdDate": "2024-02-04T10:30:00",
      "lastModifiedDate": "2024-02-04T10:30:00"
    }
  ],
  "page": {
    "number": 0,
    "size": 20,
    "totalElements": 2,
    "totalPages": 1
  }
}
```

### 6. Advanced Search

**Request:**
```bash
curl -X POST "http://localhost:8080/v1/students/search?name=Kumar&city=Bangalore&minCgpa=8.0&maxCgpa=9.5&maxBacklogs=1"
```

## Pagination

### 1. First Page (Default)

**Request:**
```bash
curl "http://localhost:8080/v1/students?page=0&size=10"
```

### 2. Second Page

**Request:**
```bash
curl "http://localhost:8080/v1/students?page=1&size=10"
```

### 3. Custom Page Size

**Request:**
```bash
curl "http://localhost:8080/v1/students?page=0&size=50"
```

**Response Structure:**
```json
{
  "content": [...],
  "page": {
    "number": 0,
    "size": 50,
    "totalElements": 150,
    "totalPages": 3
  }
}
```

## Statistics

### Get Student Statistics

**Request:**
```bash
curl "http://localhost:8080/v1/students/statistics"
```

**Response:**
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

## Error Handling

### 1. Validation Error (400 Bad Request)

**Request (Invalid CGPA):**
```bash
curl -X POST http://localhost:8080/v1/students \
  -H "Content-Type: application/json" \
  -d '{
    "studentNumber": "STU001",
    "name": "Test Student",
    "address": {
      "street": "Test Street",
      "city": "Test City",
      "state": "Test State",
      "country": "India"
    },
    "cgpa": 12.5,
    "backlogs": 0
  }'
```

**Response:**
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
      "message": "must not exceed 10.0",
      "rejectedValue": "12.5"
    }
  ]
}
```

### 2. Student Not Found (404 Not Found)

**Request:**
```bash
curl http://localhost:8080/v1/students/STU999
```

**Response:**
```json
{
  "timestamp": "2024-02-06T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Student not found with student number: STU999",
  "path": "/v1/students/STU999"
}
```

### 3. Duplicate Student (409 Conflict)

**Request (Creating student with existing number):**
```bash
curl -X POST http://localhost:8080/v1/students \
  -H "Content-Type: application/json" \
  -d '{
    "studentNumber": "STU001",
    "name": "Another Student",
    ...
  }'
```

**Response:**
```json
{
  "timestamp": "2024-02-06T10:30:00",
  "status": 409,
  "error": "Conflict",
  "message": "Student with student number STU001 already exists",
  "path": "/v1/students"
}
```

## Postman Collection

You can import these examples into Postman using the following JSON:

```json
{
  "info": {
    "name": "Student Management API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Create Student",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "url": "http://localhost:8080/v1/students",
        "body": {
          "mode": "raw",
          "raw": "{\n  \"studentNumber\": \"STU001\",\n  \"name\": \"Rajesh Kumar\",\n  \"address\": {\n    \"street\": \"15 MG Road\",\n    \"city\": \"Bangalore\",\n    \"state\": \"Karnataka\",\n    \"country\": \"India\"\n  },\n  \"cgpa\": 8.7,\n  \"backlogs\": 0\n}"
        }
      }
    }
  ]
}
```

## Testing with HTTPie

If you prefer HTTPie over curl:

```bash
# Create student
http POST http://localhost:8080/v1/students \
  studentNumber=STU001 \
  name="Rajesh Kumar" \
  address:='{"street":"15 MG Road","city":"Bangalore","state":"Karnataka","country":"India"}' \
  cgpa:=8.7 \
  backlogs:=0

# Get student
http GET http://localhost:8080/v1/students/STU001

# Update student
http PUT http://localhost:8080/v1/students/STU001 \
  name="Rajesh Kumar" \
  cgpa:=9.0

# Delete student
http DELETE http://localhost:8080/v1/students/STU001
```

## Bulk Testing Script

For bulk testing, you can use this bash script:

```bash
#!/bin/bash

BASE_URL="http://localhost:8080/v1"

# Create multiple students
for i in {1..10}; do
  STUDENT_NUM=$(printf "STU%03d" $i)
  curl -X POST "$BASE_URL/students" \
    -H "Content-Type: application/json" \
    -d '{
      "studentNumber": "'$STUDENT_NUM'",
      "name": "Student '$i'",
      "address": {
        "street": "Street '$i'",
        "city": "City",
        "state": "State",
        "country": "India"
      },
      "cgpa": 7.5,
      "backlogs": 0
    }'
  echo ""
done

# Get all students
curl "$BASE_URL/students?page=0&size=20"
```

Save as `test-api.sh` and run:
```bash
chmod +x test-api.sh
./test-api.sh
```
