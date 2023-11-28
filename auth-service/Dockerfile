# Use a base image with Java 17
FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/authentication-0.0.1-SNAPSHOT.jar /app/

# Expose the port that your application will run on
EXPOSE 8081

# Set the entrypoint and command to run the Spring Boot application
CMD ["java", "-jar", "authentication-0.0.1-SNAPSHOT.jar"]

