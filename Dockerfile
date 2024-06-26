# Use an official Maven image to build the project
FROM maven:3.8.1-openjdk-11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the working directory
COPY . .

# Package the application
RUN mvn clean package

# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
