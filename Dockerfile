FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY target/ecommerce-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]