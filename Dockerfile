FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/licencjat-0.0.1-SNAPSHOT.jar .

RUN apk add --no-cache curl

EXPOSE 8082

CMD ["java", "-jar", "licencjat-0.0.1-SNAPSHOT.jar"]


