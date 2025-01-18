# Ecommerce APIs


Spring, Java , SQL, OpenAPI and AWS project

This project features a **[RESTful CRUD API](https://taliphus.vercel.app/api)** that processes data for an ecommerce application.

## Key product features
- Designed and fully documented using Swagger tools and OpenAPI Specification. 
  - Full **[API contract](./openapi.yaml)** can be found [here](./openapi.yaml).
- [JWT based autorization and Persistence](./auth/) enabling persistent session based logins.
- [Middleware functions](./middleware) for data validation and user authentication.
  - In-depth error handling, casting a wide net over potential edge cases and sources of error.
- [Data modelling](./prisma/schema.prisma) and [database migrations](./prisma/migrations/20230728105408_/migration.sql) with Prisma ORM.
- Programmatic [database reseeding](/prisma/seed.ts) using [dummy data](/prisma/dev_data.ts).


---
## Endpoints
For more information on available query parameters and request body requirements, visit the **[API base url](https://taliphus.vercel.app/api)**.

Many of these endpoints require authenticated access, which you can accomplish by first signing up and then logging in.

```json
ADD VIDEO DEMO HERE INSTEAD

// 1) Send a POST request to /api/signup
{
  "username": ...,
  "password": ...,
  "email": ... ,
  "name": ...
}

// 2) Send a POST request to /api/login
{
  "username": ... /* your username from step 1) */,
  "password": ... /* your password from step 1) */
}
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

### Docker Installation
1) Install Docker Desktop. 
2) Run the command below. 
```
sudo docker run -d -p 8080:8080 harshbpatel/ecommerce-app
```

### Setup instructions
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

