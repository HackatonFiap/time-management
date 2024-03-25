FROM maven:latest

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db-product:5432/timemgm
WORKDIR /app
RUN rm -rf /app/*
COPY . /app
RUN mvn clean install -DskipTests
RUN mkdir jar
RUN mv /app/target/hackaton-company-0.0.1-SNAPSHOT.jar /app/jar/timemanagement-app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/jar/timemanagement-app.jar"]
