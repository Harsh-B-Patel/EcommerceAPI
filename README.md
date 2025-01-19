# Ecommerce APIs

A RESTful CRUD API for managing data in an ecommerce application.

**Stack:** Spring, Java, SQL, OpenAPI, Docker, AWS.  
**Hosted on AWS Elastic Container Service (ECS):** [API Base URL](http://98.81.75.203:8081/)  
**Docker Image:** [DockerHub Image](https://hub.docker.com/r/harshbpatel/ecommerce-app)


## Key product features
- Designed and fully documented using Swagger tools and OpenAPI Specification. 
  - Full **[API contract](./resources/openapi.json)** can be found [here](./resources/openapi.json).
- [JWT based autorization and Persistence](./src/main/java/harsh/projects/ecommerce/service/JwtUtil.java) enabling persistent session based logins.
- [Middleware functions](./src/main/java/harsh/projects/ecommerce/service/) for data validation and user authentication.
  - In-depth error handling, casting a wide net over potential edge cases and sources of error.
- Programmatic [database reseeding](./src/main/java/harsh/projects/ecommerce/controller/ResetController.java) using [dummy data](./resources/CreateSampelTables.sql) in MySQL.


---

## Key Features

- **Comprehensive API Documentation**  
  Designed and documented with Swagger tools and OpenAPI Specification.  
  [API Contract](./resources/openapi.json)

- **JWT-Based Authorization and Persistence**  
  Persistent session-based logins using [JWT Token](./src/main/java/harsh/projects/ecommerce/service/JwtUtil.java).

- **Middleware for Validation and Authentication**  
  [Middleware functions](./src/main/java/harsh/projects/ecommerce/service/) handle data validation, user authentication, and robust error handling to address edge cases.

- **Database Reseeding**  
  Programmatic [database reseeding](./src/main/java/harsh/projects/ecommerce/controller/ResetController.java) using [dummy data](./resources/CreateSampelTables.sql) in MySQL.

---

## Endpoints
For more information on available query parameters and request body requirements, visit the **[API base url](http://98.81.75.203:8081/)**.

Many of these endpoints require authenticated access, which you can accomplish by first signing up and then logging in.

```json
ADD VIDEO DEMO HERE
```

| HTTP Method | Endpoint                | Description                  |
|-------------|-------------------------|------------------------------|
| POST        | `/signup`               | User signup                  |
| POST        | `/login`                | User login                   |
| GET         | `/user/:id`             | Retrieve user by ID          |
| POST        | `/user/:id`             | Update user details          |
| DELETE      | `/user/:id`             | Delete user by ID            |
| GET         | `/product/:id`          | Retrieve product by ID       |
| POST        | `/product/:id`          | Add a new product            |
| PUT         | `/product/:id`          | Update product details       |
| DELETE      | `/product/:id`          | Delete product by ID         |
| GET         | `/user/:id/cart`        | Retrieve user cart           |
| PUT         | `/user/:id/cart`        | Update user cart             |
| GET         | `/resetDB`              | Reset database               |
| GET         | `/setDB`                | Set database with dummy data |

---
## Dependencies

| Dependency      | Purpose                       |
|------------------|-------------------------------|
| **Spring**       | Backend API framework         |
| **AWS RDS (MySQL)** | Database                    |
| **JUnit**        | Unit testing framework        |
| **Docker**       | Containerization             |
| **AWS ECS**      | Cloud deployment platform    |

---
## Running the project on localhost
> **Prerequisites:**  
> Install Java, Maven, MySQL, and Docker on your local machine.


### Docker Instructions
1) Install Docker Desktop. 
2) Run the command below. 
```
sudo docker run -d -p 8080:8080 harshbpatel/ecommerce-app
```

### Setup Instructions
1) Clone and fork the repository and install all dependencies (Java, Maven, MySQL, Docker).
2) Create a local instance of MySQL server.
3) Set the MySQL variable in Constants Class.
4) Run below Maven Command to create jar for the Spring app.
```
mvn clean install
```
6) Either Compile and run Java Project from a Java IDE or run below Docker commands to create a docker image.
```
docker build -t ecommerce-app:latest .

docker run -p 8081:8080 ecommerce-app:latest
```

