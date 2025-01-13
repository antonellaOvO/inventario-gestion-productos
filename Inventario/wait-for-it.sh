#!/bin/bash
# wait-for-it.sh: espera a que un servicio esté disponible (en este caso, MySQL)

HOST=$1
PORT=$2
shift 2

echo "Esperando que el servicio $HOST:$PORT esté disponible..."

# Esperar hasta que MySQL esté listo
until nc -z -v -w30 $HOST $PORT; do
  echo "Esperando MySQL..."
  sleep 2
done

echo "Servicio $HOST:$PORT está disponible, continuando con la ejecución."

# Ejecutar el comando que sigue (en este caso, iniciar la aplicación)
exec "$@"
