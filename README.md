# Online Quiz API

## Overview

This is a RESTful API for creating, managing, and taking online quizzes. The application supports multiple question types (single and multiple choice), random or full quiz retrieval, answer submission, and automatic scoring. It is built with Spring Boot and exposes a clean JSON API.

## Features

- Create and manage quizzes with multiple questions and options
- Support for single and multiple choice questions
- Retrieve full quizzes or random questions for quiz taking
- Submit answers and get scored results programmatically
- RESTful API design with JSON request/response
- Basic CRUD operations for quizzes, questions, and options

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Data JPA
- MySQL (or any JPA-compatible database)
- Maven build tool

## Hosted Application

The application is hosted and available for testing at:

[https://onlinequizapi.onrender.com/swagger-ui.html](https://onlinequizapi.onrender.com/swagger-ui.html)

> Note: This app is running on a free hosting tier, so response times might be slower or delayed during periods of inactivity or traffic.

## Getting Started

### Prerequisites

- Java 21
- Maven installed
- MySQL or preferred JPA-supporting database

### Setup and Run

1. Clone the repository:

- git clone [https://github.com/MaratheHarshad/OnlineQuizAPI.git](https://github.com/MaratheHarshad/OnlineQuizAPI.git)


2. Configure database settings in 
`src/main/resources/application.properties`:


- spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
- spring.datasource.username=yourusername
- spring.datasource.password=yourpassword
- spring.jpa.hibernate.ddl-auto=update


3. Build and run the application:
- mvn clean install
- mvn spring-boot:run


4. Access APIs at `http://localhost:8080/api`

## API Endpoints (Main)

- Explore the full API via the hosted Swagger UI linked above.

## Contribution

Contributions and feedback are welcome! Please open issues or pull requests on the GitHub repository.

