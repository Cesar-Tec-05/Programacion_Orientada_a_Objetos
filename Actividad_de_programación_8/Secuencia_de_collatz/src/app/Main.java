package app;

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 06 de Octubre de 2025
 * @version 1.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 8, secuencia de collatz.
 */

/**
 * Importaciones necesarias
 */
import java.util.Scanner;
import app.Back_collatz;

/**
 * Clase Main
 * Esta clase contiene el método principal para ejecutar el programa de la secuencia de Collatz.
 * Proporciona un menú interactivo para que el usuario pueda generar la secuencia o salir del programa.
 */
public class Main {
    /**
     * Método principal
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        while (true) { // Bucle principal del menú
            print("====== Programa Secuencia de Collatz ======"); 
            print("1. Generar secuencia");
            print("2. Salir");
            print("----------------------------------------------");
            int opcion = scann("Seleccione una opción (1-2):");
            while (opcion < 1 || opcion > 2) { // Validar opción
                print("Opción inválida. Por favor, intente de nuevo.");
                opcion = scann("Seleccione una opción (1-2):");
            }
            switch (opcion) { // Ejecutar opción seleccionada
                case 1:
                    limpiarConsola();
                    ejecutarCollatz();
                    break;
                case 2:
                    print("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
            }
            esperarTecla(); 
            limpiarConsola();
        }
    }

    /**
     * Ejecuta la generación de la secuencia de Collatz
     * Solicita al usuario un número positivo y muestra la secuencia junto con estadísticas
     */
    public static void ejecutarCollatz() {
        int n = scann("Ingrese un número entero positivo: ");
        while (n <= 0) { // Validar que el número sea positivo
            print("Error: El número debe ser positivo.");
            n = scann("Ingrese un número entero positivo: ");
        }
        Back_collatz collatz = new Back_collatz(n); // Crear instancia de Back_collatz
        collatz.generarSecuencia();
        print("\nSecuencia generada:");
        for (int num : collatz.getSecuencia()) { // Imprimir secuencia
            print(num + " ");
        }
        print("--------------------------------------------------");
        print("\nNúmero de divisiones entre 2: ", collatz.getNumeroDivisiones());
        print("Número de multiplicaciones por 3 y suma de 1: ", collatz.getNumeroMultiplicaciones());
        print("Número inicial: ", collatz.getNumeroInicial());
        print("Longitud de la secuencia: ", collatz.getSecuencia().size());
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
}
