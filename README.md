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

<p float="left">
  <img src="./images/node.svg" width="60" />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="./images/ts.svg" width="60" />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="./images/prisma.svg" width="140" />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="./images/psql.svg" width="60" /> 
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="./images/mocha.svg" width="60" /> 
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="./images/chai.svg" width="60" />
</p>

---
## Endpoints
For more information on available query parameters and request body requirements, visit the **[API base url](https://taliphus.vercel.app/api)**.

Many of these endpoints require authenticated access, which you can accomplish by first signing up and then logging in.

```json
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
POST | /api/signup
POST | /api/login
POST | /api/logout
GET | /api/products
GET | /api/products/bestsellers
GET | /api/products/:id
GET | /api/products/:id/reviws
GET, PUT, DELETE | /api/customers/:id
GET, PUT | /api/customers/:id/cart
GET, PUT | /api/customers/:id/wishlist
GET, POST | /api/customers/:id/orders
GET | /api/customers/:id/favorites
GET | /api/customers/:id/orders/:orderId
GET | /api/customers/:id/reviews
POST | /api/customers/:id/addresses
DELETE | /api/customers/:id/addresses/:addressId
GET | /api/categories
GET | /api/suppliers
GET, POST | /api/reviews
GET, PUT, DELETE | /api/reviews/:id


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
> This project requires Java, MySQL to be installed locally. 

### Setup instructions
1) Clone and fork the repository and install all dependencies.
2) Create a local empty PSQL database called **ecommerce_db**.
3) Create a **.env** file in the root of the repository with 4 environment variables:
```
DATABASE_PRISMA_URL=postgresql://<USER>:<password>@localhost:5432/ecommerce_db
DATABASE_URL_NON_POOLING=postgresql://<USER>:<password>@localhost:5432/ecommerce_db
SESSION_SECRET=sessionsecret
PORT=3000
```
4) For the database connection strings, Replace ```<USER>``` with the name of your local database user (e.g. *postgres*) and replace ```<PASSWORD>``` with whatever password you used to set up the local user.

You can now run the scripts below and begin to explore the project.

### Run the Express server in development mode.
