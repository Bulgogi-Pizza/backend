FROM gradle:8.5-jdk17 AS build
COPY . /app
WORKDIR /app
RUN gradle clean build -x test --stacktrace --no-daemon

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/auth-service-0.0.1-SNAPSHOT.jar /app/auth-service.jar
ENTRYPOINT ["java", "-jar", "/app/auth-service.jar"]
