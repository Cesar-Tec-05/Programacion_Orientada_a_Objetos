/**
 * @author Cesar de Jesus Becerra Vera
 * @since 29 de Octubre de 2025
 * @version 1.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 11 - Programa para calcular áreas y perímetros de figuras geométricas.
 */

package src.app; // PAQUETE: src.app

import java.util.Scanner; // IMPORTACIÓN DE LA CLASE SCANNER

/**
 * Clase Main que proporciona una interfaz de terminal para calcular áreas y perímetros de figuras geométricas.
 */
public class Main {

    // Inicialización de constantes para la clase Main
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SEPARADOR = "\n" + "=".repeat(50);
    
    /**
     * Método principal que ejecuta la interfaz de terminal.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Inicialización de la variable para controlar la continuación del programa
        boolean continuar = true;
        mostrarBienvenida();

        while (continuar) { // Bucle principal del programa
            try { // Manejo de excepciones para entradas inválidas
                int opcion = mostrarMenuPrincipal();
                switch (opcion) { // Selección de la figura geométrica
                    case 1 -> calcularCirculo();
                    case 2 -> calcularElipse();
                    case 3 -> calcularTriangulo();
                    case 4 -> calcularCuadrado();
                    case 5 -> calcularRectangulo();
                    case 6 -> calcularRomboide();
                    case 7 -> calcularPoligonoRegular();
                    case 8 -> {
                        mostrarDespedida();
                        continuar = false;
                    }
                }
            } catch (Exception e) { // Captura de excepciones generales
                mostrarError("Entrada inválida. Por favor, intente de nuevo.");
                scanner.nextLine(); // Limpiar buffer
            }
            
            if (continuar) { // Preguntar si el usuario desea continuar
                continuar = preguntarContinuar();
            }
        }
        scanner.close();
    }

    /**
     * Muestra el mensaje de bienvenida al usuario.
     */
    private static void mostrarBienvenida() {
        System.out.println(SEPARADOR);
        System.out.println("📐 CALCULADORA DE FIGURAS GEOMÉTRICAS 📏");
        System.out.println(SEPARADOR);
        System.out.println("Bienvenido a la calculadora de figuras geométricas");
        System.out.println("Calcula áreas y perímetros de diferentes figuras");
    }

    /**
     * Muestra el menú principal y obtiene la opción seleccionada por el usuario.
     * @return La opción seleccionada por el usuario.
     */
    private static int mostrarMenuPrincipal() {
        System.out.println("\n📋 Figuras disponibles:");
        System.out.println("1. ⭕ Círculo");
        System.out.println("2. 🔵 Elipse");
        System.out.println("3. 📐 Triángulo");
        System.out.println("4. ⬛ Cuadrado");
        System.out.println("5. ▭ Rectángulo");
        System.out.println("6. ◊ Romboide");
        System.out.println("7. 🔷 Polígono Regular");
        System.out.println("8. ❌ Salir");
        
        return obtenerOpcionValida("Seleccione una figura (1-8): ", 1, 8);
    }

    /**
     * Calcula y muestra los resultados para un círculo.
     */
    private static void calcularCirculo() {
        System.out.println("\n⭕ CÍRCULO");
        Circulo_back circulo = new Circulo_back();
        float radio = obtenerNumeroValido("Radio: ");
        circulo.setArista(radio);
        
        mostrarResultados( // Mostrar resultados del cálculo
            "Radio: " + radio,
            "Perímetro: " + String.format("%.2f", circulo.getPerimetro()),
            "Área: " + String.format("%.2f", circulo.getArea())
        );
    }

    /**
     * Calcula y muestra los resultados para una elipse.
     */
    private static void calcularElipse() {
        System.out.println("\n🔵 ELIPSE");
        Elipse_back elipse = new Elipse_back();
        float semiEjeMayor = obtenerNumeroValido("Semi-eje mayor: ");
        float semiEjeMenor = obtenerNumeroValido("Semi-eje menor: ");
        
        elipse.setArista(semiEjeMayor);
        elipse.setArista2(semiEjeMenor);
        
        mostrarResultados( // Mostrar resultados del cálculo
            "Semi-eje mayor: " + semiEjeMayor,
            "Semi-eje menor: " + semiEjeMenor,
            "Perímetro: " + String.format("%.2f", elipse.getPerimetro()),
            "Área: " + String.format("%.2f", elipse.getArea())
        );
    }

    /**
     * Calcula y muestra los resultados para un triángulo.
     */
    private static void calcularTriangulo() {
        System.out.println("\n📐 TRIÁNGULO");
        Triangulo_back triangulo = new Triangulo_back();
        
        float lado1 = obtenerNumeroValido("Lado 1: ");
        float lado2 = obtenerNumeroValido("Lado 2: ");
        float lado3 = obtenerNumeroValido("Lado 3: ");
        float base = obtenerNumeroValido("Base: ");
        float altura = obtenerNumeroValido("Altura: ");
        
        triangulo.setArista(lado1);
        triangulo.setArista2(lado2);
        triangulo.setArista3(lado3);
        triangulo.setBase(base);
        triangulo.setAltura(altura);
        
        mostrarResultados( // Mostrar resultados del cálculo
            "Lados: " + lado1 + ", " + lado2 + ", " + lado3,
            "Base: " + base,
            "Altura: " + altura,
            "Perímetro: " + String.format("%.2f", triangulo.getPerimetro()),
            "Área: " + String.format("%.2f", triangulo.getArea())
        );
    }

    /**
     * Calcula y muestra los resultados para un cuadrado.
     */
    private static void calcularCuadrado() {
        System.out.println("\n⬛ CUADRADO");
        Cuadrado_back cuadrado = new Cuadrado_back();
        float lado = obtenerNumeroValido("Lado: ");
        cuadrado.setArista(lado);
        
        mostrarResultados( // Mostrar resultados del cálculo
            "Lado: " + lado,
            "Perímetro: " + String.format("%.2f", cuadrado.getPerimetro()),
            "Área: " + String.format("%.2f", cuadrado.getArea())
        );
    }

    /**
     * Calcula y muestra los resultados para un rectángulo.
     */
    private static void calcularRectangulo() {
        System.out.println("\n▭ RECTÁNGULO");
        Rectangulo_back rectangulo = new Rectangulo_back();
        float base = obtenerNumeroValido("Base: ");
        float altura = obtenerNumeroValido("Altura: ");
        
        rectangulo.setArista(base);
        rectangulo.setArista2(altura);

        mostrarResultados( // Mostrar resultados del cálculo
            "Base: " + base,
            "Altura: " + altura,
            "Perímetro: " + String.format("%.2f", rectangulo.getPerimetro()),
            "Área: " + String.format("%.2f", rectangulo.getArea())
        );
    }

    /**
     * Calcula y muestra los resultados para un romboide.
     */
    private static void calcularRomboide() {
        System.out.println("\n◊ ROMBOIDE");
        Romboide_back romboide = new Romboide_back();
        float base = obtenerNumeroValido("Base: ");
        float altura = obtenerNumeroValido("Altura: ");
        float ladoOblicuo = obtenerNumeroValido("Lado oblicuo: ");
        
        romboide.setArista(base);
        romboide.setArista2(altura);
        romboide.setLado_oblicuo(ladoOblicuo);
        
        mostrarResultados( // Mostrar resultados del cálculo
            "Base: " + base,
            "Altura: " + altura,
            "Lado oblicuo: " + ladoOblicuo,
            "Perímetro: " + String.format("%.2f", romboide.getPerimetro()),
            "Área: " + String.format("%.2f", romboide.getArea())
        );
    }

    /**
     * Calcula y muestra los resultados para un polígono regular.
     */
    private static void calcularPoligonoRegular() {
        System.out.println("\n🔷 POLÍGONO REGULAR");
        Poligono_regular_back poligono = new Poligono_regular_back();
        float lado = obtenerNumeroValido("Longitud del lado: ");
        int numLados = (int) obtenerNumeroValido("Número de lados (3-12): ", 3, 12);
        
        poligono.setArista(lado);
        poligono.setNumLados(numLados);
        poligono.getApotema();
        
        mostrarResultados( // Mostrar resultados del cálculo
            "Lado: " + lado,
            "Número de lados: " + numLados,
            "Apotema: " + String.format("%.2f", poligono.getApotema()),
            "Perímetro: " + String.format("%.2f", poligono.getPerimetro()),
            "Área: " + String.format("%.2f", poligono.getArea())
        );
    }

    /**
     * Obtiene un número válido del usuario dentro de un rango especificado.
     * @param mensaje El mensaje a mostrar al usuario.
     * @param min El valor mínimo permitido.
     * @param max El valor máximo permitido.
     * @return El número válido ingresado por el usuario.
     */
    private static float obtenerNumeroValido(String mensaje) {
        return obtenerNumeroValido(mensaje, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    /**
     * Obtiene un número válido del usuario dentro de un rango especificado.
     * @param mensaje El mensaje a mostrar al usuario.
     * @param min El valor mínimo permitido.
     * @param max El valor máximo permitido.
     * @return El número válido ingresado por el usuario.
     */
    private static float obtenerNumeroValido(String mensaje, float min, float max) {
        float numero;
        do {
            System.out.print(mensaje);
            while (!scanner.hasNextFloat()) { // Validación de entrada
                System.out.println("❌ Por favor ingrese un número válido.");
                System.out.print(mensaje);
                scanner.next();
            }
            numero = scanner.nextFloat();
            if (numero < min || numero > max) { // Validación de rango
                System.out.printf("❌ El número debe estar entre %.0f y %.0f%n", min, max);
            }
        } while (numero < min || numero > max); // Repetir hasta obtener un número válido
        return numero;
    }

    /**
     * Obtiene una opción válida del usuario dentro de un rango especificado.
     * @param mensaje El mensaje a mostrar al usuario.
     * @param min El valor mínimo permitido.
     * @param max El valor máximo permitido.
     * @return La opción válida ingresada por el usuario.
     */
    private static int obtenerOpcionValida(String mensaje, int min, int max) {
        return (int) obtenerNumeroValido(mensaje, min, max);
    }

    /**
     * Muestra los resultados del cálculo al usuario.
     * @param resultados Los resultados a mostrar.
     */
    private static void mostrarResultados(String... resultados) {
        System.out.println("\n✨ Resultados:");
        System.out.println("-".repeat(30));
        for (String resultado : resultados) { // Iterar y mostrar cada resultado
            System.out.println(resultado);
        }
        System.out.println("-".repeat(30));
    }

    /**
     * Muestra un mensaje de error al usuario.
     * @param mensaje El mensaje de error a mostrar.
     */
    private static void mostrarError(String mensaje) {
        System.out.println("\n❌ Error: " + mensaje);
    }

    /**
     * Pregunta al usuario si desea continuar con otro cálculo.
     * @return true si el usuario desea continuar, false en caso contrario.
     */
    private static boolean preguntarContinuar() {
        System.out.print("\n🤔 ¿Desea calcular otra figura? (s/n): ");
        String respuesta = scanner.next().toLowerCase();
        return respuesta.startsWith("s");
    }

    /**
     * Muestra un mensaje de despedida al usuario.
     */
    private static void mostrarDespedida() {
        System.out.println(SEPARADOR);
        System.out.println("👋 ¡Gracias por usar la calculadora geométrica!");
        System.out.println("Que tenga un excelente día");
        System.out.println(SEPARADOR);
    }
}