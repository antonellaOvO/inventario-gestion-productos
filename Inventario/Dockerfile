FROM openjdk:22-jdk-slim

# Copiar el archivo JAR y el script de espera
COPY Inventario/target/Inventario-1.0-SNAPSHOT.jar app_inventario.jar
COPY Inventario/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 8080

# Usar el script de espera antes de iniciar la aplicación Spring Boot
ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--", "java", "-jar", "/app_inventario.jar"]
