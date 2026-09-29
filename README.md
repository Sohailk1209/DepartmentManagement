# Department Management System

A RESTful **Department Management System** built using **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**.

This project demonstrates how to build a clean layered Spring Boot application with CRUD operations, DTOs, validation, exception handling, partial updates using Java Reflection, and standardized API responses.

---

## 🚀 Features

* Create a department
* Get all departments
* Get a department by ID
* Update a department completely
* Partially update a department
* Delete a department
* Prevent duplicate department titles
* Custom uppercase title validation
* Global exception handling
* Standardized API response structure
* PostgreSQL database integration
* DTO-to-Entity mapping using ModelMapper
* JPA/Hibernate integration
* Automatic department creation timestamp
* Lombok for reducing boilerplate code

---

## 🛠️ Tech Stack

| Technology         | Usage                         |
| ------------------ | ----------------------------- |
| Java 21            | Programming Language          |
| Spring Boot 4.1.1  | Backend Framework             |
| Spring Web MVC     | REST API development          |
| Spring Data JPA    | Database interaction          |
| Hibernate          | ORM                           |
| PostgreSQL         | Relational Database           |
| ModelMapper        | DTO ↔ Entity mapping          |
| Jakarta Validation | Request validation            |
| Lombok             | Boilerplate reduction         |
| Maven              | Dependency management & build |

---

## 📁 Project Structure

```text
DepartmentManagement
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sohail
│   │   │           └── DepartmentManagement
│   │   │               │
│   │   │               ├── advices
│   │   │               │   ├── Error.java
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   ├── GlobalResponseHandler.java
│   │   │               │   └── Response.java
│   │   │               │
│   │   │               ├── configuration
│   │   │               │   └── AppConfiguration.java
│   │   │               │
│   │   │               ├── controller
│   │   │               │   └── DepartmentController.java
│   │   │               │
│   │   │               ├── dto
│   │   │               │   └── DepartmentDTO.java
│   │   │               │
│   │   │               ├── entity
│   │   │               │   └── Department.java
│   │   │               │
│   │   │               ├── exception
│   │   │               │   ├── DuplicateResourceFoundException.java
│   │   │               │   └── ResourceNotFoundException.java
│   │   │               │
│   │   │               ├── repository
│   │   │               │   └── DepartmentRepository.java
│   │   │               │
│   │   │               ├── service
│   │   │               │   ├── DepartmentService.java
│   │   │               │   └── impl
│   │   │               │       └── DepartmentServiceImpl.java
│   │   │               │
│   │   │               ├── validation
│   │   │               │   ├── OnlyUpperCase.java
│   │   │               │   └── OnlyUpperCaseValidator.java
│   │   │               │
│   │   │               └── DepartmentManagementApplication.java
│   │   │
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.yaml
│   │
│   └── test
│       └── java
│           └── com
│               └── sohail
│                   └── DepartmentManagement
│                       └── DepartmentManagementApplicationTests.java
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# 🏗️ Architecture

The application follows a layered architecture:

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
PostgreSQL
```

Additional components:

```text
DTO
 │
 └── Request/Response data transfer

Validation
 │
 └── Request validation

Exception Handler
 │
 └── Centralized error handling

Response Handler
 │
 └── Standardized API responses
```

---

# 🗄️ Database Configuration

The project uses **PostgreSQL**.

Create a PostgreSQL database:

```sql
CREATE DATABASE department_db;
```

The application is configured to connect to:

```text
jdbc:postgresql://localhost:5432/department_db
```

Update the following properties in:

```text
src/main/resources/application.yaml
```

```yaml
spring:
  application:
    name: DepartmentManagement

  datasource:
    url: jdbc:postgresql://localhost:5432/department_db
    username: YOUR_POSTGRES_USERNAME
    password: YOUR_POSTGRES_PASSWORD
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
```

> **Note:** Do not commit real database credentials to GitHub. Use environment variables or a local configuration file for production projects.

---

# 📦 Installation & Setup

## 1. Clone the repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

Navigate into the project:

```bash
cd DepartmentManagement
```

---

## 2. Configure PostgreSQL

Make sure PostgreSQL is running.

Create the database:

```sql
CREATE DATABASE department_db;
```

Then configure your PostgreSQL username and password in:

```text
src/main/resources/application.yaml
```

---

## 3. Build the project

Using Maven:

```bash
./mvnw clean install
```

On Windows:

```bash
mvnw.cmd clean install
```

---

## 4. Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

# 🔗 API Endpoints

Base URL:

```text
http://localhost:8080/department
```

---

## 1. Create Department

### Endpoint

```http
POST /department/createDepartment
```

### Request Body

```json
{
  "title": "COMPUTER SCIENCE",
  "isActive": true
}
```

### Example cURL

```bash
curl -X POST http://localhost:8080/department/createDepartment \
-H "Content-Type: application/json" \
-d '{
  "title": "COMPUTER SCIENCE",
  "isActive": true
}'
```

### Response

The API returns the created department wrapped inside the standard response structure.

---

# 2. Get All Departments

### Endpoint

```http
GET /department/getAllDepartment
```

### Example

```bash
curl http://localhost:8080/department/getAllDepartment
```

---

# 3. Get Department By ID

### Endpoint

```http
GET /department/getDepartmentById/{id}
```

### Example

```http
GET /department/getDepartmentById/1
```

---

# 4. Update Department

Updates the complete department resource.

### Endpoint

```http
PUT /department/updateDepartmentWithId/{id}
```

### Example

```http
PUT /department/updateDepartmentWithId/1
```

### Request Body

```json
{
  "title": "INFORMATION TECHNOLOGY",
  "isActive": true
}
```

---

# 5. Partially Update Department

The project supports partial updates using HTTP PATCH.

### Endpoint

```http
PATCH /department/partialUpdateDepartmentWithId/{id}
```

### Example

```http
PATCH /department/partialUpdateDepartmentWithId/1
```

### Request Body

For example, only updating the title:

```json
{
  "title": "INFORMATION TECHNOLOGY"
}
```

Or only updating the active status:

```json
{
  "isActive": false
}
```

The implementation uses **Java Reflection** to identify the fields supplied in the request and update only those fields.

---

# 6. Delete Department

### Endpoint

```http
DELETE /department/deleteDepartmentWithId/{id}
```

### Example

```http
DELETE /department/deleteDepartmentWithId/1
```

---

# 7. Secret Message Endpoint

### Endpoint

```http
GET /department/getSecretMessage
```

### Example

```bash
curl http://localhost:8080/department/getSecretMessage
```

---

# 🧩 Department Entity

The main entity contains:

| Field       | Type            | Description                                |
| ----------- | --------------- | ------------------------------------------ |
| `id`        | `Long`          | Auto-generated primary key                 |
| `title`     | `String`        | Unique department title                    |
| `isActive`  | `Boolean`       | Department active status                   |
| `createdAt` | `LocalDateTime` | Automatically generated creation timestamp |

The department title is configured as:

```text
Unique
Not Null
```

---

# ✅ Validation

The project contains a custom validation annotation:

```text
@OnlyUpperCase
```

Department titles are expected to contain uppercase letters.

For example:

### Valid

```json
{
  "title": "COMPUTER SCIENCE",
  "isActive": true
}
```

### Invalid

```json
{
  "title": "Computer Science",
  "isActive": true
}
```

The custom validator checks each character and rejects lowercase letters and non-letter characters.

---

# ⚠️ Exception Handling

The application uses centralized exception handling through:

```text
@RestControllerAdvice
```

The project currently handles:

### Resource Not Found

```text
ResourceNotFoundException
```

Used when a department with the requested ID does not exist.

Example:

```text
DEPARTMENT NOT FOUND WITH ID : 10
```

---

### Duplicate Resource

```text
DuplicateResourceFoundException
```

Used when attempting to create or update a department with a title that already exists.

---

### Validation Errors

```text
MethodArgumentNotValidException
```

Validation failures are also handled globally.

---

# 📋 Standard Response Structure

The project uses a generic response wrapper:

```json
{
  "data": {},
  "error": null,
  "timeStamp": "2026-01-01T12:00:00"
}
```

For an error response:

```json
{
  "data": null,
  "error": {
    "status": "NOT_FOUND",
    "timeStamp": "2026-01-01T12:00:00",
    "subErrors": [
      "DEPARTMENT NOT FOUND WITH ID : 10"
    ]
  },
  "timeStamp": "2026-01-01T12:00:00"
}
```

The `GlobalResponseHandler` provides consistent response formatting, while `GlobalExceptionHandler` handles application exceptions centrally.

---

# 🔄 DTO and Entity Mapping

The application separates API models from database entities.

### DTO

```text
DepartmentDTO
```

### Entity

```text
Department
```

**ModelMapper** is used to convert between them.

```text
DepartmentDTO
      │
      ▼
 ModelMapper
      │
      ▼
Department Entity
```

This helps keep the API layer decoupled from the persistence layer.

---

# 🧠 Partial Update Using Reflection

One of the notable implementations in this project is the PATCH operation.

The service dynamically examines the fields of the `Department` entity:

```text
Request
   │
   ▼
Map<String, Object>
   │
   ▼
Check Department fields
   │
   ▼
Update only supplied fields
   │
   ▼
Save entity
```

Java Reflection is used to access the entity fields dynamically.

This allows a request such as:

```json
{
  "isActive": false
}
```

to update only `isActive` without replacing the entire department object.

---

# 🧪 Testing

The project contains Spring Boot test infrastructure under:

```text
src/test/java
```

Run tests using:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

---

# 🔐 Important Security Note

Database credentials should **not** be committed directly to the repository.

For local development, configure:

```yaml
spring:
  datasource:
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
```

For production, use environment variables or a secure secrets-management solution.

---

# 📌 Future Improvements

Possible improvements for future versions include:

* Add pagination and sorting
* Add search/filter functionality
* Add Swagger/OpenAPI documentation
* Add unit tests for service and controller layers
* Add integration tests
* Improve PATCH validation and type conversion
* Prevent updates to immutable fields such as `id` and `createdAt`
* Add authentication and authorization using Spring Security
* Add Docker support
* Add database migration using Flyway or Liquibase
* Add proper logging
* Add CI/CD pipeline
* Improve API versioning
* Add automated API documentation

---

# 👨‍💻 Author

**Sohail Khan**

B.Tech — Information Technology

---

# ⭐ Project Highlights

This project demonstrates practical implementation of:

* RESTful API development
* Spring Boot layered architecture
* CRUD operations
* Spring Data JPA
* PostgreSQL integration
* DTO pattern
* ModelMapper
* Custom Bean Validation
* Global exception handling
* Global response handling
* Java Reflection
* Partial resource updates
* Maven project management

---

## 📄 License

This project is intended for learning and educational purposes.
