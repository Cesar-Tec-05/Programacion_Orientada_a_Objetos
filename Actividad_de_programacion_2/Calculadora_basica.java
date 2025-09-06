/**
 * @author Cesar de Jesus Becerra Vera
 * @since 01 de Septiembre de 2025
 * @version 1.0
 * PROGRAMA: Calculadora_basica.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 2, calculadora básica.
 */

/**
 * Importar la clase Scanner para entrada de datos
 */
import java.util.Scanner; // Importar la clase Scanner para entrada de datos

/**
 * Clase principal para la calculadora básica
 */
public class Calculadora_basica {
    /**
     * Función principal del programa
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        boolean condition = true;
        print ("-- Programa de Calculadora Basica --");
        while (condition) { // Bucle principal
            print ("Ingrese el primer numero: ");
            float num1 = scanner.nextFloat();
            print ("Ingrese el segundo numero: ");
            float num2 = scanner.nextFloat();
            print ("Seleccione la operacion (1: suma, 2: resta, 3: multiplicacion, 4: division): ");
            String operacion = scanner.next();
            float resultado = 0;
            switch (operacion) { // Selección de operación
                case "1":
                    resultado = suma(num1, num2);
                    break;
                case "2":
                    resultado = resta(num1, num2);
                    break;
                case "3":
                    resultado = multiplicacion(num1, num2);
                    break;
                case "4":
                    resultado = division(num1, num2);
                    break;
                default:
                    print ("Operacion no valida");
                    break;
            }
            print ("El resultado es: ", resultado);
            print ("Desea realizar otra operacion? (s/n): ");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) { // Si la respuesta no es "s", salir del bucle
                condition = false;
            }
            limpiarConsola(); // Limpiar consola
        }
        System.exit(0); // Salir del programa
    }

    /**
     * Objeto Scanner para entrada de datos
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Imprimir un mensaje en la consola
     * @param imprimir Mensaje a imprimir
     */
    public static void print(String imprimir) {
        System.out.println(imprimir);
    }
    /**
     * Sobrecargar el método print para imprimir un mensaje y un valor
     * @param texto Mensaje a imprimir
     * @param valor Valor a imprimir
     */
    public static void print(String texto, Object valor) {
        System.out.println(texto + valor);
    }
    /**
     * Sobrecargar el método print para imprimir varios argumentos
     * @param args Argumentos a imprimir
     */
    public static void print(Object... args) {
        StringBuilder result = new StringBuilder();
        for (Object arg : args) {
            result.append(arg);
        }
        System.out.println(result);
    }

    /**
     * Función para sumar dos números
     * @param a Primer número
     * @param b Segundo número
     * @return a + b
     */
    public static float suma(float a, float b) {
        return a + b;
    }

    /**
     * Función para restar dos números
     * @param a Primer número
     * @param b Segundo número
     * @return a - b
     */
    public static float resta(float a, float b) {
        return a - b;
    }

    /**
     * Función para multiplicar dos números
     * @param a Primer número
     * @param b Segundo número
     * @return a * b
     */
    public static float multiplicacion(float a, float b) {
        return a * b;
    }

    /**
     * Función para dividir dos números
     * @param a Primer número
     * @param b Segundo número
     * @return a / b
     */
    public static float division(float a, float b) {
        if (b != 0 && a != 0) {
            return a / b;
        } else {
            System.out.println("Error de sintaxis: División por cero no permitida.");
            return Float.NaN;
        }
    }

    /**
     * Función para limpiar la consola
     */
    public static void limpiarConsola() {
        try {
            String sistemaOperativo = System.getProperty("os.name");
            if (sistemaOperativo.contains("Windows")) { // Para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else { // Para Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) { // Si falla el método nativo, usar enfoque alternativo
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}