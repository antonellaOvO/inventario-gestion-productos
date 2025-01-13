#!/usr/bin/env bash

host="$1"
port="$2"

echo "Comprobando conexión a $host en el puerto $port..."

while ! echo > /dev/tcp/"$host"/"$port"; do
  echo "Esperando MySQL en $host:$port..."
  sleep 1
done

echo "MySQL disponible en $host:$port, ejecutando la aplicación."
exec "${@:3}"
