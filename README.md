# Library System

A simple library system API built with Spring Boot and Gradle.

## Features

- Register a new borrower to the library.
- Register a new book to the library.
- Get a list of all books in the library.
- Borrow a book with a particular book id.
- Return a borrowed book.

## Data Models

### Borrower
- `id`: Unique identifier.
- `name`: Name of the borrower.
- `email`: Email address of the borrower.

### Book
- `id`: Unique identifier.
- `isbn`: ISBN number of the book.
- `title`: Title of the book.
- `author`: Author of the book.
## Prerequisites

- JDK 17 or higher
- Docker (optional, for containerization)
- Gradle (if not using the provided Gradle Wrapper)

## Build and Run the Project

### Using Gradle Wrapper

You can use the provided Gradle Wrapper to build and run the project.

#### Build the Project

```bash
./gradlew build

#### Run the Project

./gradlew bootRun
```

### Build the Docker Image
1. Ensure that Docker is installed and running on your machine.
2. Build the Docker image using the following command:
```bash
docker build -t library-service .
```

### Run the Docker Container
Run the Docker container from the image you just built:
```bash
docker run -p 8080:8080 library-service
```