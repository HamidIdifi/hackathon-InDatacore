# hackathon-InDatacore
This repository contains the backend codebase for the full-stack application built with Spring Boot.
The backend handles data processing, exposes RESTful APIs, and interacts with a MySql database.
## Getting Started

### Technologies Used

- Spring Boot
- Java 11
- Swagger UI


### Dependencies

We can use the Spring Initializr to generate a new Spring Boot project
with the following dependencies for our project:
- Spring web
- Spring Data JPA
- MySQL Database
- Lombok
- OpenCSV
- Mockito/ JUnit
- springfox Open API

Once we have created the project, we need to configure our application properties to connect to the database. We can do this by adding the following properties in our application.properties file:

```bash
server.port=8081
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/hackathon-InDatacore-app?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
# config swagger ui
spring.mvc.pathmatch.matching-strategy=${SWAGGER_MATCHING_STRATEGY:ANT_PATH_MATCHER}

```

## Features

### CSV File Handling
- Reads a .CSV file at startup to populate the database with objects (1 object per line).

### RESTful APIs
1. **GET API:** Retrieves a list of objects in JSON format.
2. **POST API:** Adds a specified object to the database.
3. **POST API with Random Values:** Adds an object to the database with randomly generated values.

### Swagger UI Documentation
- Utilizes Swagger UI for clear and interactive API documentation: http://localhost:8081/swagger-ui/index.html#/
- All APIs are easily testable through Swagger UI.

### Packaging
- The application is packaged as a WAR (Web Application Archive) file.

### Installation

1. Clone the repository:
  ```bash
  git clone https://github.com/HamidIdifi/hackathon-InDatacore.git
  ```
2. Navigate to the project directory:
  ```bash
  cd hackathon-InDatacore
  ```
3. Build the project:
  ```bash
  mvn clean install
  ```
4. Run the application:
  ```bash
  mvn spring-boot:run
  ```
