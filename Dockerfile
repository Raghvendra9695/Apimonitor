
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

RUN mvn -f apimonitor/pom.xml clean package -DskipTests


FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

COPY --from=build /app/apimonitor/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]