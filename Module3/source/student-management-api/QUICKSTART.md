# Quick Start Guide

Get the Student Management API up and running in 5 minutes!

## Prerequisites

- ✅ Java 17 or higher installed
- ✅ Maven 3.6 or higher installed
- ✅ Your favorite API client (Postman, curl, or browser)

### Verify Prerequisites

```bash
java -version    # Should show Java 17+
mvn -version     # Should show Maven 3.6+
```

## Step 1: Extract and Navigate

```bash
unzip student-management-api.zip
cd student-management-api
```

## Step 2: Build the Project

```bash
mvn clean install
```

Expected output:
```
[INFO] BUILD SUCCESS
[INFO] Total time: 15 s
```

## Step 3: Run the Application

### Option A: With Maven (Development)

```bash
mvn spring-boot:run
```

### Option B: With Demo Data

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=demo
```

### Option C: As JAR (Production-like)

```bash
java -jar target/student-management-api-1.0.0.jar
```

### Option D: With Demo Data (JAR)

```bash
java -jar target/student-management-api-1.0.0.jar --spring.profiles.active=demo
```

Expected output:
```
Started StudentManagementApiApplication in 2.5 seconds
```

## Step 4: Verify It's Running

### Option A: Browser
Open http://localhost:8080/v1/health

Expected response:
```json
{
  "status": "UP",
  "timestamp": "2024-02-06T10:30:00"
}
```

### Option B: curl
```bash
curl http://localhost:8080/v1/health
```

## Step 5: Explore the API

### Access Swagger UI
Open http://localhost:8080/swagger-ui.html

This provides an interactive interface to test all API endpoints!

### Quick API Test

#### Create a Student
```bash
curl -X POST http://localhost:8080/v1/students \
  -H "Content-Type: application/json" \
  -d '{
    "studentNumber": "STU001",
    "name": "John Doe",
    "address": {
      "street": "123 Main St",
      "city": "Mumbai",
      "state": "Maharashtra",
      "country": "India"
    },
    "cgpa": 8.5,
    "backlogs": 0
  }'
```

#### Get All Students
```bash
curl http://localhost:8080/v1/students
```

#### Get Student by Number
```bash
curl http://localhost:8080/v1/students/STU001
```

## Common Issues & Solutions

### Issue: Port 8080 already in use

**Solution 1: Change the port**
```bash
java -jar target/student-management-api-1.0.0.jar --server.port=8081
```

**Solution 2: Kill the process using port 8080**
```bash
# On Linux/Mac
lsof -ti:8080 | xargs kill -9

# On Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

### Issue: Java version mismatch

**Error:** `Unsupported class file major version`

**Solution:** Ensure you're using Java 17 or higher
```bash
java -version
# If wrong version, update JAVA_HOME environment variable
```

### Issue: Maven build fails

**Solution:** Clean Maven cache and rebuild
```bash
mvn clean
rm -rf ~/.m2/repository/com/university
mvn install
```

## Next Steps

1. **Read the API Documentation**
   - See `API_EXAMPLES.md` for detailed usage examples
   - See `ARCHITECTURE.md` for architecture details

2. **Run the Tests**
   ```bash
   mvn test
   ```

3. **Explore Swagger UI**
   - http://localhost:8080/swagger-ui.html
   - Try out different endpoints interactively

4. **Check Application Metrics**
   - http://localhost:8080/actuator/health
   - http://localhost:8080/actuator/info
   - http://localhost:8080/actuator/metrics

## Demo Data

When running with the `demo` profile, 10 sample students are automatically loaded:

- STU001: Rajesh Kumar (Bangalore, CGPA: 8.7)
- STU002: Priya Sharma (Mumbai, CGPA: 9.5)
- STU003: Amit Patel (Mumbai, CGPA: 8.8)
- STU004: Sneha Reddy (Hyderabad, CGPA: 7.9)
- STU005: Vikram Singh (Delhi, CGPA: 9.2)
- STU006: Anjali Desai (Pune, CGPA: 8.3)
- STU007: Karthik Menon (Chennai, CGPA: 7.5)
- STU008: Divya Iyer (Bangalore, CGPA: 9.0)
- STU009: Rohan Gupta (Bangalore, CGPA: 8.1)
- STU010: Meera Krishnan (Kochi, CGPA: 8.9)

## Testing the API

### Get Statistics
```bash
curl http://localhost:8080/v1/students/statistics
```

### Search Students
```bash
curl "http://localhost:8080/v1/students?city=Bangalore&minCgpa=8.5"
```

### Get Paginated Results
```bash
curl "http://localhost:8080/v1/students?page=0&size=5&sortBy=cgpa&sortOrder=desc"
```

## Stopping the Application

Press `Ctrl + C` in the terminal where the application is running.

## Development Tips

### Auto-reload During Development
The project includes Spring Boot DevTools. Changes to code will automatically trigger a restart:

1. Make code changes
2. Save the file
3. Application automatically restarts

### View Logs
Logs are printed to console by default. To change log level:

Edit `src/main/resources/application.yml`:
```yaml
logging:
  level:
    com.university.studentapi: DEBUG  # Change to INFO, WARN, or ERROR
```

### Customize Configuration
Edit `src/main/resources/application.yml` to customize:
- Server port
- Logging levels
- Actuator endpoints
- Swagger UI path

## Production Deployment

For production deployment:

1. **Build optimized JAR**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Run with production profile**
   ```bash
   java -jar target/student-management-api-1.0.0.jar \
     --spring.profiles.active=prod \
     --server.port=8080
   ```

3. **Consider:**
   - Using a process manager (systemd, PM2)
   - Setting up reverse proxy (nginx)
   - Configuring external logging
   - Setting up monitoring
   - Using external configuration

## Need Help?

- 📖 Read `README.md` for comprehensive documentation
- 🏗️ Read `ARCHITECTURE.md` for architecture details
- 💡 Check `API_EXAMPLES.md` for usage examples
- 🌐 Visit Swagger UI: http://localhost:8080/swagger-ui.html

## Summary

You should now have:
- ✅ Application running on http://localhost:8080
- ✅ Swagger UI accessible
- ✅ API endpoints responding
- ✅ Sample data loaded (if using demo profile)

**Happy coding! 🚀**
