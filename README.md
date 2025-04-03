# Library Management System

## Overview
This is a Spring Boot-based Digital Library Book Management System that allows librarians to efficiently manage book records.

## Features
- Add new books
- View all books
- Search books by ID or title
- Update book details
- Delete book records

## Prerequisites
- Java 17
- Maven
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Setup and Installation
1. Clone the repository
2. Open the project in your preferred IDE
3. Run `mvn clean install` to download dependencies
4. Run the application using `mvn spring-boot:run`

## API Endpoints
- `POST /api/books`: Add a new book
- `GET /api/books`: View all books
- `GET /api/books/id/{bookId}`: Search book by ID
- `GET /api/books/title/{title}`: Search book by title
- `PUT /api/books/{bookId}`: Update book details
- `DELETE /api/books/{bookId}`: Delete a book

## Testing
Run unit tests using `mvn test`

## Deployment 
The application can be packaged and deployed using `mvn package`

## Live Service Link
[Click here to access the application](https://bookmanagementsystem-mz9e.onrender.com/api/books)

## Challenges and Improvements
- Implemented basic validation and exception handling
- Could add more robust logging
- Could enhance search capabilities
- Could add authentication and authorization

## Contribution :
All rights reserved to "Omkar Yadav".
