FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17.0.1-jdk-slim
MAINTAINER phunguyen
COPY --from=build /target/Daily-Reflect-Backend-0.0.1-SNAPSHOT.jar Daily-Reflect-Backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Daily-Reflect-Backend.jar"]