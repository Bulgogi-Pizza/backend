FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/hub-transit-route-service-0.0.1-SNAPSHOT.jar /app/hub-transit-route-service.jar
ENTRYPOINT ["java", "-jar", "/app/hub-transit-route-service.jar"]