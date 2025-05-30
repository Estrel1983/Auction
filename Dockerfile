FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/auction-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]