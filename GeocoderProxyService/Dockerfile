FROM maven:3.8.4-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

FROM openjdk:18-ea-11-alpine3.15
COPY --from=build /workspace/target/*.jar geocoder-0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","geocoder-0.1.jar"]