# Use an official Maven image with OpenJDK 17 to build the project
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the working directory
COPY . .

# Package the application and skip tests
RUN mvn clean package -DskipTests

# Use an official OpenJDK 17 runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Add this line to start your Java application
ENTRYPOINT ["java", "-jar", "app.jar"]
