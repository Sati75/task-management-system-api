# Task Management System API

A secure RESTful Task Management System built using Java, Spring Boot, Spring Security, JWT Authentication, and MySQL.

This application allows users to register, log in, and manage their personal tasks through REST APIs. Each user can access only their own tasks.

## Features

- User registration and login
- JWT-based authentication and authorization
- Password encryption using BCrypt
- Create, retrieve, update, and delete tasks
- Update task status
- User-specific task access and data isolation
- Global exception handling
- Duplicate email validation
- REST API documentation using Swagger UI
- MySQL database integration

## Tech Stack

| Category | Technologies |
|---|---|
| Language | Java 21 |
| Backend | Spring Boot 4.1.1 |
| Security | Spring Security, JWT |
| Database | MySQL |
| ORM | Spring Data JPA, Hibernate |
| Build Tool | Gradle |
| API Testing | Postman |
| API Documentation | Swagger UI |
| Password Encryption | BCrypt |
| IDE | IntelliJ IDEA |

## Project Structure

```text
task-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/satitulasi/taskmanagementsystem/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── TaskManagementSystemApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── build.gradle
```

## API Endpoints

### Authentication

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | Public |
| POST | `/auth/login` | Authenticate user and receive JWT | Public |
| GET | `/auth/test` | Test authentication endpoint | Public |

### Task Management

| Method | Endpoint | Description |
|---|---|---|
| POST | `/tasks` | Create a task |
| GET | `/tasks` | Retrieve logged-in user's tasks |
| GET | `/tasks/{id}` | Retrieve a task by ID |
| PUT | `/tasks/{id}` | Update a task |
| PATCH | `/tasks/{id}/status?status=COMPLETED` | Update task status |
| DELETE | `/tasks/{id}` | Delete a task |

**Note:** Task endpoints require a valid JWT access token.

## Task Status

Tasks support the following statuses:

- `TODO`
- `IN_PROGRESS`
- `COMPLETED`

## Security

- Spring Security protects task management endpoints.
- JWT tokens are used for stateless authentication.
- BCrypt is used to securely hash user passwords.
- Users can access and manage only their own tasks.
- Database queries enforce user-level task ownership.

## Database Configuration

The application uses MySQL.

Create the database:

```sql
CREATE DATABASE task_management_db;
```

Configure the following environment variables before starting the application:

| Variable | Description |
|---|---|
| `DB_URL` | MySQL database connection URL |
| `DB_USERNAME` | MySQL username |
| `DB_PASSWORD` | MySQL password |
| `JWT_SECRET` | Secret key used for JWT signing |

Do not commit actual passwords, JWT secrets, or other credentials to GitHub.

## Run the Application

### Prerequisites

- Java 21
- MySQL Server
- IntelliJ IDEA or another Java IDE
- Git

### Steps

1. Clone the repository:

```bash
git clone https://github.com/Sati75/task-management-system-api.git
```

2. Open the project in IntelliJ IDEA.

3. Configure the required environment variables.

4. Make sure MySQL is running and the database exists.

5. Run the Spring Boot application:

```bash
./gradlew bootRun
```

On Windows:

```bash
gradlew.bat bootRun
```

The application runs on:

```text
http://localhost:8082
```

## Swagger API Documentation

After starting the application, open:

```text
http://localhost:8082/swagger-ui/index.html
```

Use Swagger UI to explore and test the available REST APIs.

## Testing

The application was manually tested using Postman and Swagger UI.

Verified scenarios include:

- User registration and login
- JWT authentication
- Task creation and retrieval
- Task updates and status changes
- Task deletion
- Duplicate email handling
- Unauthorized task access
- User-level data isolation

## Future Enhancements

- Pagination and sorting
- Task search and filtering
- Unit and integration test expansion
- Docker containerization
- Cloud deployment

## Author

**Sati Tulasi**

Java Backend Developer | Spring Boot | REST APIs | MySQL | Spring Security
