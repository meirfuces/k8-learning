# Use a lightweight Java 21 runtime as the base image
FROM eclipse-temurin:21-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"] 