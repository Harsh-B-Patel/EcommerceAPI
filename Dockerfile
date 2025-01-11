FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY src/main/resources/static/openapi.json /app/static/openapi.json
COPY target/ecommerce-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]