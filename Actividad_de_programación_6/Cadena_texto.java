/**
 * @author Cesar de Jesus Becerra Vera
 * @since 22 de Septiembre de 2025
 * @version 1.0
 * PROGRAMA: Cadena_texto.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 6, Elaborar un programa en el que se le pregunte alguna cadena de texto larga al usuario final y que el programa regrese, a través de métodos, 1 por cada acción siguiente:
 *  Cantidad de vocales
 *  Cantidad de palabras
 *  Cantidad de consonantes
 *  Número de caracteres en total
 */

/**
 * Importar la clase Scanner para la entrada de datos
 */
import java.util.Scanner;

/**
 * Clase principal del programa
 */
public class Cadena_texto {
    /** Método principal del programa
     * @param args Argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        while (true) {
            limpiarConsola();
            print ("==== Programa Analizador de Cadenas de Texto ====");
            String cadena = scann("Ingrese una cadena de texto larga: ", "");
            print ("----------------------------------------------------");
            print(cantidadVocales(cadena));
            print(cantidadPalabras(cadena));
            print(cantidadConsonantes(cadena));
            print(numeroCaracteres(cadena));
            print ("----------------------------------------------------");
            esperarTecla();
            String repetir = scann("¿Desea salir del programa? (s/n): ", "");
            if (repetir.equalsIgnoreCase("s")) {
                break;
            }
        }
        System.exit(0);
    }

    /**
     * Método para contar la cantidad de vocales en una cadena de texto
     * @param cadena Cadena de texto a analizar
     * @return Cantidad de vocales en la cadena
     */
    public static String cantidadVocales(String cadena) {
        int contador = 0;
        String vocales = "aeiouAEIOUáéíóúÁÉÍÓÚ";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (vocales.indexOf(c) != -1) {
                contador++;
            }
        }
        return "Cantidad de vocales: " + contador;
    }

    /**
     * Método para contar la cantidad de palabras en una cadena de texto
     * @param cadena Cadena de texto a analizar
     * @return Cantidad de palabras en la cadena
     */
    public static String cantidadPalabras(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return "Cantidad de palabras: 0";
        }
        String[] palabras = cadena.trim().split("\\s+");
        return "Cantidad de palabras: " + palabras.length;
    }

    /**
     * Método para contar la cantidad de consonantes en una cadena de texto
     * @param cadena Cadena de texto a analizar
     * @return Cantidad de consonantes en la cadena
     */
    public static String cantidadConsonantes(String cadena) {
        int contador = 0;
        String consonantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (consonantes.indexOf(c) != -1) {
                contador++;
            }
        }
        return "Cantidad de consonantes: " + contador;
    }

    /**
     * Método para contar el número de caracteres en una cadena de texto
     * @param cadena Cadena de texto a analizar
     * @return Número de caracteres en la cadena
     */
    public static String numeroCaracteres(String cadena) {
        return "Número de caracteres en total: " + cadena.length();
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
