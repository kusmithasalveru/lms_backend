# LMS Backend (Spring Boot, MySQL)
Lightweight Spring Boot backend for a Learning Management System.

## Run
1. Install Java 17+ and Maven.
2. Create MySQL database `lms_db` and update `src/main/resources/application.properties` with credentials.
3. Build and run:
```
mvn clean package
java -jar target/lms-backend-0.0.1-SNAPSHOT.jar
```

## Features
- Signup / Login (basic, returns dummy token)
- Courses CRUD
- Assignments and Submissions
- Calendar Events
- Profile editing

This is a minimal starter intended to be extended (replace dummy token with full JWT, add validation, file uploads, role checks).
