FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/user-service-0.0.1-SNAPSHOT.jar.jar /app/user-service.jar
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]