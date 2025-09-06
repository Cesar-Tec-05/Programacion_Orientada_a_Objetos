/**
 * @author Cesar de Jesus Becerra Vera
 * @since 03 de Septiembre de 2025
 * @version 1.0
 * PROGRAMA: Ciclos.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 3, tablas de multiplicar, serie fibonacci y numeros primos con ciclos.
 */

/**
 * Importar la clase Scanner para entrada de datos
 */
import java.util.Scanner;

/**
 * Clase principal para demostrar el uso de ciclos
 */
public class Ciclos {
    /**
     * Función principal del programa
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        String opcion;
        while (true) { // Bucle infinito para el menú
            opcion = menu();
            limpiarConsola();
            switch (opcion) { // Selección de la opción del menú
                case "1":
                    tablasDeMultiplicar();
                    break;
                case "2":
                    serieFibonacci();
                    break;
                case "3":
                    numerosPrimos();
                    break;
                case "4":
                    print("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    print("Opción no válida. Intente de nuevo.");
            }
            esperarTecla();
            String respuesta = scann("Quieres salir del programa? (s/n): ", "");
            limpiarConsola();
            if (respuesta.equalsIgnoreCase("s")) { // Salir del programa si el usuario lo desea
                print("Saliendo del programa...");
                System.exit(0);
            }
        }
    }

    /**
     * Objeto Scanner para entrada de datos
     */
    static Scanner scanner = new Scanner(System.in);
    /**
     * Leer un número entero con manejo de errores
     * @param mensaje Mensaje a mostrar al usuario
     * @return El número entero ingresado por el usuario
     */
    public static int scann(String mensaje) {
        System.out.println(mensaje);
        int valor = 0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                String linea = scanner.nextLine();
                valor = Integer.parseInt(linea);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número entero válido.");
                System.out.println(mensaje);
            }
        }
        
        return valor;
    }
    /**
     * Leer un número decimal con manejo de errores
     * @param mensaje Mensaje a mostrar al usuario
     * @param placeholder Valor por defecto si no se ingresa ningún valor
     * @return El número decimal ingresado por el usuario
     */
    public static double scann(String mensaje, double placeholder) {
        System.out.println(mensaje);
        double valor = 0.0;
        boolean entradaValida = false;
        
        while (!entradaValida) {
            try {
                String linea = scanner.nextLine();
                valor = Double.parseDouble(linea);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número decimal válido.");
                System.out.println(mensaje);
            }
        }
        
        return valor;
    }
    /**
     * Leer un carácter con manejo de errores
     * @param mensaje Mensaje a mostrar al usuario
     * @param placeholder Valor por defecto si no se ingresa ningún valor
     * @return El carácter ingresado por el usuario
     */
    public static char scann(String mensaje, char placeholder) {
        System.out.println(mensaje);
        String entrada = scanner.nextLine();
        
        if (entrada.isEmpty()) {
            System.out.println("Error: No se ingresó ningún carácter.");
            return scann(mensaje, placeholder);
        }
        
        return entrada.charAt(0);
    }
    /**
     * Leer una respuesta sí/no con manejo de errores
     * @param mensaje Mensaje a mostrar al usuario
     * @param placeholder Valor por defecto si no se ingresa ningún valor
     * @return true si la respuesta es sí, false si es no
     */
    public static boolean scann(String mensaje, boolean placeholder) {
        System.out.println(mensaje + " (s/n): ");
        while (true) {
            String respuesta = scanner.nextLine().toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si")) {
                return true;
            } else if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            } else {
                System.out.println("Error: Por favor ingrese 's' para sí o 'n' para no.");
                System.out.println(mensaje + " (s/n): ");
            }
        }
    }
    /**
     * Leer una cadena de texto
     * @param mensaje Mensaje a mostrar al usuario
     * @param placeholder Valor por defecto si no se ingresa ningún valor
     * @return La cadena de texto ingresada por el usuario
     */
    public static String scann(String mensaje, String placeholder) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

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
     * Esperar a que el usuario presione una tecla
     */
    public static void esperarTecla() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Limpiar la consola (funciona en Windows, Linux y MacOS)
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

    /**
     * Mostrar el menú principal y obtener la opción del usuario
     * @return La opción seleccionada por el usuario
     */
    public static String menu() {
        print ("=== Programa de Ciclos ===");
        print ("1. Tablas de Multiplicar (ciclo for)");
        print ("2. Serie Fibonacci (ciclo while)");
        print ("3. Números Primos (ciclo do-while)");
        print ("4. Salir");
        print ("===========================");
        String opcion = scann("Seleccione una opción:", "");
        return opcion;
    }

    /**
     * Mostrar la tabla de multiplicar de un número hasta un límite usando un ciclo for
     */
    public static void tablasDeMultiplicar() {
        print("=== Tablas de Multiplicar ===");
        int numero = (int) scann("Ingrese un numero entero para ver su tabla de multiplicar:", 0);
        int limite = (int) scann("Ingrese el limite hasta dónde desea ver la tabla de multiplicar:", 0);
        print("Tabla de multiplicar del " + numero + " hasta " + limite + ":");
        for (int i = 1; i <= limite; i++) {
            print(numero + " x " + i + " = " + (numero * i));
        }
    }

    /**
     * Mostrar la serie Fibonacci hasta un número de términos usando un ciclo while
     */
    public static void serieFibonacci() {
        print("=== Serie Fibonacci ===");
        int terminos = (int) scann("Ingrese cuántos términos de la serie Fibonacci desea ver:", 0);
        int a = 0, b = 1;
        print("Serie Fibonacci:");
        int count = 0;
        while (count < terminos) {
            print(a + " ");
            int siguiente = a + b;
            a = b;
            b = siguiente;
            count++;
        }
    }

    /**
     * Verificar si un número es primo
     * @param numero Número a verificar
     * @return true si el número es primo, false en caso contrario
     */
    public static boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }
    /**
     * Mostrar todos los números primos hasta un límite usando un ciclo do-while
     */
    public static void numerosPrimos() {
        print("=== Números Primos ===");
        int limite = (int) scann("Ingrese el límite hasta dónde desea encontrar números primos:", 0);
        print("Números primos hasta " + limite + ":");
        int i = 2;
        do {
            if (esPrimo(i)) {
                print(i + " ");
            }
            i++;
        } while (i <= limite);
    }
}
