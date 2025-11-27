# 🚀 Proyecto Final - Programación Orientada a Objetos

**Autor:** Cesar de Jesus Becerra Vera  
**Fecha:** 26 de Noviembre de 2025  
**Institución:** Centro Universitario de los Altos / Universidad de Guadalajara  
**Carrera:** Ingeniería en Computación - 3er Semestre  
**Profesor:** Sergio Franco Casillas

---

## 📋 Descripción

Programa con interfaz gráfica que integra tres herramientas principales:

1. **Calculadora Básica** - Operaciones aritméticas fundamentales
2. **Conversor de Bases Numéricas** - Conversión entre binario, octal, decimal y hexadecimal
3. **Calculador de Propiedades Geométricas** - Áreas y perímetros de figuras

---

## 📁 Estructura del Proyecto

```
Proyecto/
├── src/
│   ├── app/                    # Interfaz gráfica
│   │   ├── Main.java
│   │   ├── PanelCalculadora.java
│   │   ├── PanelConversor.java
│   │   └── PanelGeometria.java
│   ├── back_calculadora/       # Lógica de calculadora
│   │   └── Back_calculadora_basica.java
│   ├── back_conversor/         # Lógica de conversor
│   │   └── Conversor_unidiades_back.java
│   └── back_geometria/         # Lógica de geometría
│       ├── Figuras_back.java
│       ├── Circulo_back.java
│       ├── Cuadrado_back.java
│       ├── Elipse_back.java
│       ├── Poligono_regular_back.java
│       ├── Rectangulo_back.java
│       ├── Romboide_back.java
│       └── Triangulo_back.java
├── dist/                       # Ejecutable de producción (generado)
├── doc/                        # Documentación Javadoc
├── bin/                        # Archivos compilados (desarrollo)
├── build-prod.sh               # Script de construcción
└── MANIFEST.MF                 # Manifiesto del JAR
```

---

## 🔧 Requisitos

### Para Desarrollo
- **JDK (Java Development Kit)** 8 o superior
- Editor de código o IDE (VS Code, IntelliJ IDEA, Eclipse, etc.)

### Para Ejecución
- **JRE (Java Runtime Environment)** 8 o superior

---

## 🏗️ Compilar a Producción

### Método Automático (Recomendado)

El proyecto incluye un script automatizado que compila y empaqueta todo:

```bash
# 1. Navegar a la carpeta del proyecto
cd {RUTA_DEL_PROYECTO}

# 2. Dar permisos de ejecución al script (solo la primera vez)
chmod +x build-prod.sh

# 3. Ejecutar el script de construcción
./build-prod.sh
```

### ¿Qué hace el script?

El script `build-prod.sh` realiza automáticamente:

1. ✅ **Limpia** el directorio `dist/` (elimina compilaciones anteriores)
2. ✅ **Compila** todos los archivos `.java` de los paquetes:
   - `src.back_calculadora`
   - `src.back_conversor`
   - `src.back_geometria`
   - `src.app`
3. ✅ **Empaqueta** todo en un archivo JAR ejecutable: `ProyectoFinal.jar`
4. ✅ **Genera** scripts de ejecución multiplataforma:
   - `ejecutar.sh` (Linux/macOS)
   - `ejecutar.bat` (Windows)
5. ✅ **Crea** documentación para el usuario final: `README.txt`
6. ✅ **Limpia** archivos temporales de compilación

### Salida del Script

```
==========================================
  CONSTRUCCIÓN DE EJECUTABLE DE PRODUCCIÓN
==========================================

🧹 Limpiando directorio de distribución...
🔨 Compilando código fuente...
✅ Compilación exitosa

📦 Creando archivo JAR ejecutable...
✅ JAR creado exitosamente

🧹 Limpiando archivos temporales...
📝 Creando script de ejecución...
✅ Scripts de ejecución creados

==========================================
  ✅ CONSTRUCCIÓN COMPLETADA
==========================================

📁 Archivos generados en: ./dist

Contenido:
  - ProyectoFinal.jar    (Ejecutable principal)
  - ejecutar.sh          (Script Linux/macOS)
  - ejecutar.bat         (Script Windows)
  - README.txt           (Instrucciones)
```

---

## 🎯 Ejecutar la Aplicación

### Desde el Ejecutable de Producción (dist/)

Después de compilar a producción, tienes varias opciones:

#### En Linux/macOS:
```bash
cd dist
./ejecutar.sh
```

#### En Windows:
```cmd
cd dist
ejecutar.bat
```

#### Multiplataforma (con Java instalado):
```bash
java -jar dist/ProyectoFinal.jar
```

### Desde el Código Fuente (Desarrollo)

```bash
# Compilar manualmente
javac -d bin src/back_calculadora/*.java src/back_conversor/*.java src/back_geometria/*.java src/app/*.java

# Ejecutar
java -cp bin src.app.Main
```

---

## 📦 Distribuir la Aplicación

Para compartir tu aplicación con otros usuarios:

1. **Ejecuta el script de construcción:**
   ```bash
   ./build-prod.sh
   ```

2. **Comprime la carpeta `dist/`:**
   ```bash
   cd ..
   zip -r ProyectoFinal.zip Proyecto/dist/
   ```
   
   O en formato tar.gz:
   ```bash
   tar -czf ProyectoFinal.tar.gz Proyecto/dist/
   ```

3. **Comparte el archivo comprimido**
   - Los usuarios solo necesitan descomprimir y ejecutar
   - No necesitan instalar herramientas de desarrollo
   - Solo requieren Java Runtime Environment (JRE)

---

## 🛠️ Compilación Manual (Avanzado)

Si prefieres compilar manualmente sin usar el script:

```bash
# 1. Crear directorio de salida
mkdir -p dist/build

# 2. Compilar backend (calculadora, conversor, geometría)
javac -d dist/build -encoding UTF-8 \
    src/back_calculadora/*.java \
    src/back_conversor/*.java \
    src/back_geometria/*.java

# 3. Compilar interfaz gráfica
javac -d dist/build -cp dist/build -encoding UTF-8 \
    src/app/*.java

# 4. Crear archivo JAR
cd dist/build
jar cfm ../ProyectoFinal.jar ../../MANIFEST.MF src/

# 5. Limpiar archivos temporales
cd ../..
rm -rf dist/build
```

---

## 📝 Notas Importantes

### Estructura de Paquetes
- **NO modificar** la estructura de paquetes (`src.app`, `src.back_*`)
- Los nombres de paquetes están codificados en el código fuente
- El MANIFEST.MF referencia `src.app.Main` como clase principal

### Codificación
- Todos los archivos usan **UTF-8**
- El script incluye `-encoding UTF-8` para evitar problemas con caracteres especiales

### Compatibilidad
- El JAR generado es compatible con Java 8+
- Funciona en Windows, Linux y macOS
- La interfaz usa Swing (incluido en Java estándar)

---

## 🐛 Solución de Problemas

### Error: "comando no encontrado: javac"
**Solución:** Instala el JDK
```bash
# Ubuntu/Debian
sudo apt install default-jdk

# Fedora
sudo dnf install java-latest-openjdk-devel

# macOS
brew install openjdk
```

### Error: "Main-Class no encontrada"
**Solución:** Verifica que el archivo `MANIFEST.MF` existe y contiene:
```
Manifest-Version: 1.0
Main-Class: src.app.Main
Created-By: Cesar de Jesus Becerra Vera
```

### Error al ejecutar: "ClassNotFoundException"
**Solución:** Recompila usando el script `build-prod.sh`

### El script no tiene permisos de ejecución
**Solución:**
```bash
chmod +x build-prod.sh
```

---

## 📚 Características Técnicas

### Interfaz Gráfica
- **Framework:** Java Swing
- **Tema:** Oscuro personalizado
- **Diseño:** Pestañas (JTabbedPane)
- **Responsive:** Layouts flexibles

### Calculadora
- Operaciones: suma, resta, multiplicación, división
- Manejo de decimales
- Cambio de signo
- Validación de entrada

### Conversor
- Bases soportadas: 2, 8, 10, 16
- Conversión bidireccional
- Validación de entrada por base

### Geometría
- Figuras: círculo, cuadrado, rectángulo, triángulo, elipse, romboide, polígono regular
- Cálculo de área y perímetro
- Validación de valores

---

## 📄 Licencia

Este proyecto fue desarrollado como parte del curso de Programación Orientada a Objetos en el Centro Universitario de los Altos, Universidad de Guadalajara.

---

## 👨‍💻 Autor

**Cesar de Jesus Becerra Vera**  
Ingeniería en Computación - 3er Semestre  
Centro Universitario de los Altos, UdeG

---

## 🎓 Agradecimientos

Profesor: Sergio Franco Casillas  
:D

---

**¡Gracias por usar este programa!** 🎉
