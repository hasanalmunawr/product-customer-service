# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Set timezone
ENV TZ=Asia/Jakarta
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# App JAR
COPY --from=build /app/target/*.jar app.jar

# Environment variables (can be overridden by Docker Compose or GitHub Secrets)
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM maven:3.8.4-openjdk-17 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests
#
#FROM openjdk:17-jdk-slim
#ARG APP_NAME=customer-service-management
#WORKDIR /app
#
#ENV TZ=Asia/Jakarta
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#
#COPY --from=build /app/target/*.jar customer-service-manager.jar
#ENTRYPOINT ["java", "-jar", "/app/customer-service-manager.jar", \
#            "--spring.profiles.active=prod", \
#            "--spring.datasource.username=${POSTGRES_USER}", \
#            "--spring.datasource.password=${POSTGRES_PASSWORD}", \
#            "--spring.datasource.url=${POSTGRES_URL}", \
#            "--application.security.jwt.secret-key=${JWT_SECRET_KEY}"]
#
#
