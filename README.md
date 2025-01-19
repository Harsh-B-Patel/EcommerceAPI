# Ecommerce APIs

This project features a RESTful CRUD API that processes data for an ecommerce application.

Stack: Spring, Java, SQL, OpenAPI, Docker and AWS.

Currently this project is hosted on AWS Elastic Container Service (ECS). **[API base url](http://98.81.75.203:8081/)**.

You can also find the container image on Docker Hub. **[DockerHub Image url](https://hub.docker.com/r/harshbpatel/ecommerce-app)**.

## Key product features
- Designed and fully documented using Swagger tools and OpenAPI Specification. 
  - Full **[API contract](./resources/openapi.json)** can be found [here](./resources/openapi.json).
- [JWT based autorization and Persistence](./src/main/java/harsh/projects/ecommerce/service/JwtUtil.java) enabling persistent session based logins.
- [Middleware functions](./src/main/java/harsh/projects/ecommerce/service/) for data validation and user authentication.
  - In-depth error handling, casting a wide net over potential edge cases and sources of error.
- Programmatic [database reseeding](./src/main/java/harsh/projects/ecommerce/controller/ResetController.java) using [dummy data](./resources/CreateSampelTables.sql) in MySQL.


---
## Endpoints
For more information on available query parameters and request body requirements, visit the **[API base url](http://98.81.75.203:8081/)**.

Many of these endpoints require authenticated access, which you can accomplish by first signing up and then logging in.

```json
ADD VIDEO DEMO HERE
```

| HTTP method(s) | URL
|---|---|
POST | /signup
POST | /login
GET | /user/:id
POST | /user/:id
DELETE | /user/:id
GET | /product/:id
POST | /product/:id
PUT | /product/:id
DELETE | /product/:id
GET | /user/:id/cart
PUT | /user/:id/cart
GET | /resetDB
GET | /setDB

---
## Main project dependencies
| Package | Purpose
|---|---|
Spring | Backend API framework
AWS RDS(MySQL) | Database 
JUnit | Unit testing
Docker | Containerization
AWS ECS | Cloud Deployment Platform 

---
## Running the project on localhost
> This project requires Java, Maven, MySQL and Docker to be installed locally. 

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

