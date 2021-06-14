FROM maven:3.6.1 AS MAVEN_BUILD

WORKDIR /api-courses-store-admin
COPY . .

RUN mvn clean package

FROM openjdk:8-jdk-alpine
WORKDIR /

COPY --from=MAVEN_BUILD ./api-courses-store-admin/target/api-courses-store-admin-0.0.1-SNAPSHOT.jar api-courses-store-admin/api-courses-store-admin.jar
WORKDIR /api-courses-store-admin
#CMD ["java", "-jar", "api-courses-store-admin.jar"]

EXPOSE 8081
