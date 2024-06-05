FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17.0.1-jdk-slim
MAINTAINER phunguyen
COPY --from=build /target/To-Do-Backend-0.0.1-SNAPSHOT.jar To-Do-Backend.jar
ENTRYPOINT ["java","-jar","To-Do-Backend.jar"]