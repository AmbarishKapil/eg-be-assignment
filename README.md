# Invoice Management System

## Overview

The Invoice Management System is a simple implementation designed to manage invoices. It provides functionalities to create, retrieve, update, and delete invoices. The system is built using Spring Boot and includes in-memory storage for invoices.

## Table of Contents

1. [Getting Started](#getting-started)
2. [Project Structure](#project-structure)
3. [Dependencies](#dependencies)
4. [Running the Application](#running-the-application)
5. [API Endpoints](#api-endpoints)
6. [Testing](#testing)
7. [Docker Support](#docker-support)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Docker (optional, for containerized deployment)

### Setup

1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd assignmentbe
   ```

2. **Build the project:**
   ```sh
   mvn clean install
   ```

3. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

## Project Structure

```
assignmentbe
├── src
│   ├── main
│   │   ├── java
│   │   │   └── dk
│   │   │       └── eg
│   │   │           └── global
│   │   │               └── assignmentbe
│   │   │                   ├── controllers
│   │   │                   │   └── InvoiceController.java
│   │   │                   ├── dtos
│   │   │                   │   ├── InvoiceDTO.java
│   │   │                   │   ├── InvoiceViews.java
│   │   │                   │   └── PaymentDTO.java
│   │   │                   ├── repositories
│   │   │                   │   ├── InvoiceInMemoryRepository.java
│   │   │                   │   └── InvoiceRepository.java (commented out)
│   │   │                   ├── services
│   │   │                   │   └── InvoiceService.java
│   │   │                   └── AssignmentbeApplication.java
│   │   └── resources
│   │       └── application.properties
│   ├── test
│   │   └── java
│   │       └── dk
│   │           └── eg
│   │               └── global
│   │                   └── assignmentbe
│   │                       └── AssignmentbeApplicationTests.java
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Dependencies

The project uses the following dependencies:

- Spring Boot Starter Web
- Spring Boot Starter Test
- Hibernate Validator
- Lombok

These dependencies are defined in the `pom.xml` file.

## Running the Application

To run the application, use the following command:

```sh
mvn spring-boot:run
```

The application will start on port 8080 by default.

## API Endpoints

### InvoiceController

- **Get All Invoices**
  - **Endpoint:** `GET /invoices`
  - **Description:** Retrieves a list of all invoices.
  - **Response:** `200 OK` with a list of `InvoiceDTO`.

### Example of `InvoiceDTO`

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO {
    @JsonView({InvoiceViews.Id.class, InvoiceViews.Full.class, InvoiceViews.ToCreate.class})
    long id;
    // other fields...
}
```

## Testing


```

## Docker Support

The project includes a `docker-compose.yml` file for containerized deployment.

The application will be available on port 8080.

---

This documentation provides a basic overview and setup instructions for the Invoice Management System. For more detailed information, refer to the source code and comments within the project files.
