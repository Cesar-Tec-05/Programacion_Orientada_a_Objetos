/**
 * @author Cesar de Jesus Becerra Vera
 * @since 08 de Septiembre de 2025
 * @version 1.0
 * PROGRAMA: Arreglo_numeros.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 4, manejo de arreglos unidimensionales, insertar, ver y eliminar numeros con un limite de 10.
 */

/**
 * Importar la clase Scanner para entrada de datos
 */
import java.util.Scanner;

/**
 * Clase principal para manejar un arreglo de números enteros
 */
public class Arreglo_numeros {
    /**
     * Función principal del programa
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        String opcion;
        int[] numeros = new int[10]; // Declarar el arreglo de números
        while (true) { // Bucle infinito para el menú
            opcion = menu();
            limpiarConsola();
            switch (opcion) { // Selección de la opción del menú
                case "1":
                    insertarNumero(numeros);
                    break;
                case "2":
                    verNumeros(numeros);
                    break;
                case "3":
                    eliminarNumero(numeros);
                    break;
                case "4":
                    print("Saliendo del programa...");
                    System.exit(0); // Salir del programa
                    break;
                default:
                    print("Opción no válida. Intente de nuevo.");
            }
            esperarTecla();
            limpiarConsola();
        }
    }

    /**
     * Mostrar el menú de opciones
     * @return La opción seleccionada por el usuario
     */
    public static String menu() {
        String temp_opcion;
        print("==== Arreglo de Números Enteros ====");
        print("1. Insertar número");
        print("2. Ver números");
        print("3. Eliminar número");
        print("4. Salir");
        print("----------------------------");
        temp_opcion = scann("Seleccione una opción (1-4): ", "");
        return temp_opcion;
    }

    /**
     * Insertar un número en el arreglo
     * @param numeros El arreglo de números
     */
    public static void insertarNumero(int[] numeros) {
        int numero = scann("Ingrese un número entero para insertar: ");
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == 0) { // Encontrar la primera posición vacía
                numeros[i] = numero;
                print("Número " + numero + " insertado en la posición " + i + ".");
                return;
            }
        }
        print("El arreglo está lleno. No se puede insertar más números.");
    }

    /**
     * Ver los números en el arreglo
     * @param numeros El arreglo de números
     */
    public static void verNumeros(int[] numeros) {
        print("Números en el arreglo:");
        boolean vacio = true;
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] != 0) { // Mostrar solo las posiciones que no están vacías
                print("Posición " + i + ": " + numeros[i]);
                vacio = false;
            }
        }
        if (vacio) {
            print("El arreglo está vacío.");
        }
    }

    /**
     * Eliminar un número del arreglo
     * @param numeros El arreglo de números
     */
    public static void eliminarNumero(int[] numeros) {
        int numero = scann("Ingrese el numero entero que desea eliminar: ");
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == numero) { // Encontrar el número a eliminar
                numeros[i] = 0; // Eliminar el número (marcar como vacío)
                print("Número " + numero + " eliminado de la posición " + i + ".");
                return;
            }
        }
        print("Número " + numero + " no encontrado en el arreglo.");
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
