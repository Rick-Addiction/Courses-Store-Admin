FROM maven:3.6.1 AS MAVEN_BUILD

WORKDIR /
COPY ./api-courses-store-admin ./api-courses-store-admin
WORKDIR ./api-courses-store-admin

RUN mvn clean package

FROM adoptopenjdk/openjdk11:latest
COPY --from=MAVEN_BUILD ./api-courses-store-admin/target/api-courses-store-admin-0.0.1-SNAPSHOT.jar ./api-courses-store-admin/api-courses-store-admin.jar

CMD ["java", "-jar", "./api-courses-store-admin/api-courses-store-admin.jar"]
EXPOSE 8081