# Company Resource Server Demo

A demo Spring Boot application that provides a RESTful API for managing companies and their associated contacts. This project demonstrates basic CRUD operations, API versioning, and integration with an in-memory H2 database.

## Features

- **Company Management**: Create, read, update, and delete companies
- **Contact Management**: Manage contacts associated with companies (email, phone, mobile)
- **API Versioning**: Supports versioned API endpoints (currently v1)
- **Swagger Documentation**: Interactive API documentation via Swagger UI
- **H2 Database**: In-memory database with console access for development
- **Validation**: Input validation using Spring Boot validation

## Technologies Used

- **Spring Boot 4.0.6**: Framework for building the application
- **Java 25**: Programming language
- **H2 Database**: In-memory relational database
- **Spring Web**: For RESTful web services
- **Spring JDBC**: For database operations
- **SpringDoc OpenAPI**: For API documentation
- **Lombok**: For reducing boilerplate code
- **Maven**: Build tool

## Prerequisites

- Java 25 or higher
- Maven 3.6+ (or use the included Maven wrapper)

## License

This project is licensed under the [Apache 2.0 License](LICENSE-2.0.txt).
