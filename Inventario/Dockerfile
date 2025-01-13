
FROM openjdk:22-jdk-slim

# Instalar curl para descargar Dockerize
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Descargar Dockerize
RUN curl -sSL https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz | tar -C /usr/local/bin -xz

COPY Inventario/target/Inventario-1.0-SNAPSHOT.jar app_inventario.jar
EXPOSE 8080
# Usar Dockerize para esperar a MySQL antes de ejecutar la aplicación
ENTRYPOINT ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "60s", "java", "-jar", "/app_inventario.jar"]