
FROM openjdk:22-jdk-slim
COPY Inventario/target/Inventario-1.0-SNAPSHOT.jar app_inventario.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app_inventario.jar"]