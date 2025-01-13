FROM openjdk:22-jdk-slim

# Instalar netcat (elige una opción)
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

# Copiar el archivo .jar de la aplicación
COPY Inventario/target/Inventario-1.0-SNAPSHOT.jar app_inventario.jar

# Copiar el script de espera (wait-for-it.sh)
COPY Inventario/wait-for-it.sh /wait-for-it.sh

# Asegurarse de que el script tiene permisos de ejecución
RUN chmod +x /wait-for-it.sh

# Exponer el puerto de la aplicación
EXPOSE 8080

# Usar el script de espera para esperar a MySQL antes de ejecutar la aplicación
ENTRYPOINT ["/wait-for-it.sh", "mysql", "3306", "--", "java", "-jar", "/app_inventario.jar"]
