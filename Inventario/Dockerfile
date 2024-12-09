FROM openjdk:22-jdk-slim
ARG JAR_FILE=target/Inventario-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app_inventario.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_inventario.jar"]

