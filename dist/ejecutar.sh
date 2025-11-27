#!/bin/bash
# Script para ejecutar el Proyecto Final
# Ejecuta el archivo JAR

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
java -jar "$SCRIPT_DIR/ProyectoFinal.jar"
