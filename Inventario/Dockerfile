FROM openjdk:22-jdk-slim

# Instalar Dockerize para esperar a MySQL
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/* \
    && wget https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz \
    && tar -C /usr/local/bin -xvzf dockerize-linux-amd64-v0.6.1.tar.gz \
    && rm dockerize-linux-amd64-v0.6.1.tar.gz

# Copiar el archivo .jar de la aplicación
COPY Inventario/target/Inventario-1.0-SNAPSHOT.jar app_inventario.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Usar Dockerize para esperar que MySQL esté disponible y luego ejecutar la aplicación
ENTRYPOINT ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "60s", "java", "-jar", "/app_inventario.jar"]
