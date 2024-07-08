
```markdown
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

The application will start on port 8080 by default.

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
│   ├── test
│   │   └── java
│   │       └── dk
│   │           └── eg
│   │               └── global
│   │                   └── assignmentbe
|   |                       ├── entities
│   │                       │   └── InvoiceDTOTest.java
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

- **Create Invoice**
  - **Endpoint:** `POST /invoices`
  - **Description:** Creates a new invoice.
  - **Request Body:** `InvoiceDTO` with the invoice details.
  - **Response:** `201 Created` with the created `InvoiceDTO`.

- **Update Invoice Payment**
  - **Endpoint:** `POST /invoices/{id}/payments`
  - **Description:** Updates the payment details of an invoice.
  - **Request Body:** `PaymentDTO` with the payment details.
  - **Response:** `204 No Content`

- **Process Overdue Invoices**
  - **Endpoint:** `POST /process-overdue`
  - **Description:** Processes overdue invoices by applying a late fee.
  - **Request Body:** `OverdueDTO` containing `lateFee` and `overdueDays`.
  - **Response:** `204 No Content`

### Example of `InvoiceDTO`

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO {
    @JsonView({InvoiceViews.Id.class, InvoiceViews.Full.class, InvoiceViews.ToCreate.class})
    long id;

    @JsonView({InvoiceViews.Amount.class, InvoiceViews.Full.class, InvoiceViews.ToCreate.class})
    @JsonProperty("amount")
    double amount;

    @JsonView({InvoiceViews.PaidAmount.class, InvoiceViews.Full.class})
    @JsonProperty("paid_amount")
    double paidAmount;

    @JsonView({InvoiceViews.Status.class, InvoiceViews.Full.class})
    @JsonProperty("invoice_status")
    InvoiceStatus invoiceStatus;
}
```

I have leveraged @JsonView(from Jackson) to reuse the InvoiceDTO class, whenever a subset of fields are required, rather than creating a new DTO everytime.

## Testing

The project includes unit tests to ensure the functionality of the application. The tests are located in the `src/test/java` directory.

To run the tests, use the following command:

```sh
mvn test
```

### Example Test

### JUnit Test for `InvoiceDTO`

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvoiceDTOTest {

    @Test
    public void testInvoiceDTO() {
        InvoiceDTO invoice = new InvoiceDTO();
        invoice.setId(1L);
        invoice.setAmount(100.0);
        invoice.setPaidAmount(50.0);
        invoice.setInvoiceStatus(InvoiceStatus.PENDING);

        assertEquals(1L, invoice.getId());
        assertEquals(100.0, invoice.getAmount());
        assertEquals(50.0, invoice.getPaidAmount());
        assertEquals(InvoiceStatus.PENDING, invoice.getInvoiceStatus());
    }
}
```

## Docker Support

The project includes a `docker-compose.yml` file for containerized deployment.

### Running with Docker

1. **Create the executable .jar:**
   ```sh
   mvn clean package
   ```

2. **Run the Docker container:**
   ```sh
   docker-compose up --build
   ```

The application will be available on port 8080.

---

This documentation provides a basic overview and setup instructions for the Invoice Management System. For more detailed information, refer to the source code and comments within the project files.
```

This updated `README.md` file now includes all four API endpoints, a JUnit test for `InvoiceDTO`, and information about using `@JsonView` to reuse `InvoiceDTO`.
