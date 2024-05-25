FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
LABEL authors="Karl James"

ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]