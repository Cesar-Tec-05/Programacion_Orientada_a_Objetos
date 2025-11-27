#!/bin/bash

# Script de construcción para producción
# Autor: Cesar de Jesus Becerra Vera
# Fecha: 26 de Noviembre de 2025

echo "=========================================="
echo "  CONSTRUCCIÓN DE EJECUTABLE DE PRODUCCIÓN"
echo "=========================================="
echo ""

# Variables
PROJECT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DIST_DIR="$PROJECT_DIR/dist"
BUILD_DIR="$DIST_DIR/build"
SRC_DIR="$PROJECT_DIR/src"
JAR_NAME="ProyectoFinal.jar"

# Limpiar carpeta dist si existe
echo "🧹 Limpiando directorio de distribución..."
if [ -d "$DIST_DIR" ]; then
    rm -rf "$DIST_DIR"/*
fi
mkdir -p "$BUILD_DIR"

# Compilar todos los archivos Java
echo "🔨 Compilando código fuente..."
cd "$PROJECT_DIR"

# Compilar back_calculadora
javac -d "$BUILD_DIR" -encoding UTF-8 \
    "$SRC_DIR/back_calculadora/Back_calculadora_basica.java"

if [ $? -ne 0 ]; then
    echo "❌ Error al compilar back_calculadora"
    exit 1
fi

# Compilar back_conversor
javac -d "$BUILD_DIR" -encoding UTF-8 \
    "$SRC_DIR/back_conversor/Conversor_unidiades_back.java"

if [ $? -ne 0 ]; then
    echo "❌ Error al compilar back_conversor"
    exit 1
fi

# Compilar back_geometria
javac -d "$BUILD_DIR" -encoding UTF-8 \
    "$SRC_DIR/back_geometria/Figuras_back.java" \
    "$SRC_DIR/back_geometria/Circulo_back.java" \
    "$SRC_DIR/back_geometria/Cuadrado_back.java" \
    "$SRC_DIR/back_geometria/Elipse_back.java" \
    "$SRC_DIR/back_geometria/Poligono_regular_back.java" \
    "$SRC_DIR/back_geometria/Rectangulo_back.java" \
    "$SRC_DIR/back_geometria/Romboide_back.java" \
    "$SRC_DIR/back_geometria/Triangulo_back.java"

if [ $? -ne 0 ]; then
    echo "❌ Error al compilar back_geometria"
    exit 1
fi

# Compilar app (interfaz gráfica)
javac -d "$BUILD_DIR" -cp "$BUILD_DIR" -encoding UTF-8 \
    "$SRC_DIR/app/PanelCalculadora.java" \
    "$SRC_DIR/app/PanelConversor.java" \
    "$SRC_DIR/app/PanelGeometria.java" \
    "$SRC_DIR/app/Main.java"

if [ $? -ne 0 ]; then
    echo "❌ Error al compilar app"
    exit 1
fi

echo "✅ Compilación exitosa"
echo ""

# Crear archivo JAR
echo "📦 Creando archivo JAR ejecutable..."
cd "$BUILD_DIR"
jar cfm "$DIST_DIR/$JAR_NAME" "$PROJECT_DIR/MANIFEST.MF" src/

if [ $? -ne 0 ]; then
    echo "❌ Error al crear JAR"
    exit 1
fi

echo "✅ JAR creado exitosamente"
echo ""

# Limpiar archivos de compilación temporales
echo "🧹 Limpiando archivos temporales..."
rm -rf "$BUILD_DIR"

# Crear script de ejecución
echo "📝 Creando script de ejecución..."
cat > "$DIST_DIR/ejecutar.sh" << 'EOF'
#!/bin/bash
# Script para ejecutar el Proyecto Final
# Ejecuta el archivo JAR

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
java -jar "$SCRIPT_DIR/ProyectoFinal.jar"
EOF

chmod +x "$DIST_DIR/ejecutar.sh"

# Crear script de ejecución para Windows
cat > "$DIST_DIR/ejecutar.bat" << 'EOF'
@echo off
REM Script para ejecutar el Proyecto Final en Windows
java -jar "%~dp0ProyectoFinal.jar"
pause
EOF

# Crear archivo README para distribución
cat > "$DIST_DIR/README.txt" << 'EOF'
========================================
  PROYECTO FINAL - PROGRAMACIÓN ORIENTADA A OBJETOS
========================================

Autor: Cesar de Jesus Becerra Vera
Fecha: 26 de Noviembre de 2025
Institución: Centro Universitario de los Altos / Universidad de Guadalajara
Carrera: Ingeniería en Computación - 3er Semestre
Profesor: Sergio Franco Casillas

DESCRIPCIÓN:
============
Programa con interfaz gráfica que integra tres herramientas:
1. Calculadora Básica
2. Conversor de Bases Numéricas
3. Calculador de Propiedades Geométricas

REQUISITOS:
===========
- Java Runtime Environment (JRE) 8 o superior
- Sistema operativo: Windows, Linux o macOS

INSTRUCCIONES DE EJECUCIÓN:
============================

En Linux/macOS:
---------------
1. Abrir terminal en esta carpeta
2. Ejecutar: ./ejecutar.sh
   O también: java -jar ProyectoFinal.jar

En Windows:
-----------
1. Doble clic en ejecutar.bat
   O también: Doble clic en ProyectoFinal.jar
   O también: Abrir CMD y ejecutar: java -jar ProyectoFinal.jar

CONTENIDO DEL PAQUETE:
======================
- ProyectoFinal.jar    : Archivo ejecutable principal
- ejecutar.sh          : Script de ejecución para Linux/macOS
- ejecutar.bat         : Script de ejecución para Windows
- README.txt           : Este archivo

CARACTERÍSTICAS:
================
✓ Interfaz gráfica moderna con tema oscuro
✓ Calculadora básica con operaciones aritméticas
✓ Conversor entre bases numéricas (2, 8, 10, 16)
✓ Calculador de áreas y perímetros de figuras geométricas

SOPORTE:
========
Para dudas o problemas, contactar al autor.

¡Gracias por usar este programa!
EOF

echo "✅ Scripts de ejecución creados"
echo ""

# Mostrar resumen
echo "=========================================="
echo "  ✅ CONSTRUCCIÓN COMPLETADA"
echo "=========================================="
echo ""
echo "📁 Archivos generados en: $DIST_DIR"
echo ""
echo "Contenido:"
echo "  - ProyectoFinal.jar    (Ejecutable principal)"
echo "  - ejecutar.sh          (Script Linux/macOS)"
echo "  - ejecutar.bat         (Script Windows)"
echo "  - README.txt           (Instrucciones)"
echo ""
echo "Para ejecutar el programa:"
echo "  Linux/macOS: cd dist && ./ejecutar.sh"
echo "  Windows:     cd dist && ejecutar.bat"
echo "  Universal:   java -jar dist/ProyectoFinal.jar"
echo ""
echo "=========================================="
