#!/usr/bin/env bash

host="$1"
port="$2"

while ! echo > /dev/tcp/"$host"/"$port"; do
  echo "Esperando MySQL en $host:$port..."
  sleep 1
done

echo "MySQL disponible, ejecutando la aplicación."
exec "${@:3}"
