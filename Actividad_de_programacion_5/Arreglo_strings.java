/**
 * @author Cesar de Jesus Becerra Vera
 * @since 10 de Septiembre de 2025
 * @version 1.0
 * PROGRAMA: Arreglo_strings.java
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 5, manejo de arreglos unidimensionales, insertar, ver, ordenar y eliminar numeros con un limite de 10.
 */

/**
 * importar la clase Scanner para entrada de datos
 */
import java.util.Scanner;

/**
 * Clase principal del programa
 */
public class Arreglo_strings {
    /**
     * Método principal del programa
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        int opcion;
        String[] cadenas = new String[10]; // Declarar el arreglo de cadenas de texto
        while (true) { // Bucle infinito para el menú
            opcion = menu();
            limpiarConsola();
            switch (opcion) { // Selección de la opción del menú
                case 1:
                    insertarCadena(cadenas);
                    break;
                case 2:
                    mostrarArreglo(cadenas);
                    break;
                case 3:
                    verCadenas(cadenas);
                    break;
                case 4:
                    buscarCadena(cadenas);
                    break;
                case 5:
                    eliminarCadena(cadenas);
                    break;
                default:
                    print("Saliendo del programa...");
                    System.exit(0); // Salir del programa
                    break;
            }
            esperarTecla();
            limpiarConsola();
        }
    }

    /**
     * Mostrar el menú y obtener la opción del usuario
     * @return Opción seleccionada por el usuario
     */
    public static int menu() {
        int temp_opcion; // Variable temporal para la opción
        print("============== Programa de Cadenas de Texto ==============");
        print("1. Insertar y modificar cadena de texto");
        print("2. Mostrar el arreglo");
        print("3. Ver cadenas de texto de mayor a menor (con longitud)");
        print("4. Buscar cadena de texto (por contenido)");
        print("5. Eliminar cadena de texto");
        print("6. Salir");
        print("------------------------------------------------------------");
        temp_opcion = (int) scann("Seleccione una opción (1-6): ", 0); // Leer la opción del usuario
        while (temp_opcion < 1 || temp_opcion > 6) { // Validar la opción
            print("Opción fuera de rango. Debe estar entre 1 y 6.");
            temp_opcion = (int) scann("Seleccione una opción (1-6): ", 0);
        }
        return temp_opcion;
    }

    /**
     * Insertar o modificar una cadena de texto en el arreglo
     * @param cadenas Arreglo de cadenas de texto
     */
    public static void insertarCadena(String[] cadenas) {
        int indice = (int) scann("Ingrese el índice (0-9) donde desea insertar/modificar la cadena: ", 0);
        while (indice < 0 || indice > 9) { // Validar el índice
            print("Índice fuera de rango. Debe estar entre 0 y 9.");
            indice = (int) scann("Ingrese el índice (0-9) donde desea insertar/modificar la cadena: ", 0);
        }
        limpiarConsola();
        String nuevaCadena = scann("Ingrese la cadena de texto: ", "");
        limpiarConsola();
        cadenas[indice] = nuevaCadena; // Insertar o modificar la cadena en el índice especificado
        print("Cadena insertada/modificada en el índice " + indice + ": " + nuevaCadena);
    }

    /**
     * Ver las cadenas de texto ordenadas por longitud (de mayor a menor)
     * @param cadenas Arreglo de cadenas de texto
     */
    public static void verCadenas(String[] cadenas) {
        String[] cadenasOrdenadas = cadenas.clone(); // Clonar el arreglo original para no modificarlo
        for (int i = 0; i < cadenasOrdenadas.length - 1; i++) { // Ordenar el arreglo por longitud (de mayor a menor) usando burbuja
            for (int j = i + 1; j < cadenasOrdenadas.length; j++) { // Comparar cada par de elementos
                if (cadenasOrdenadas[i] == null) continue; // Saltar si el elemento es null
                if (cadenasOrdenadas[j] == null || cadenasOrdenadas[i].length() < cadenasOrdenadas[j].length()) { // Si el segundo es null o el primero es menor que el segundo
                    String temp = cadenasOrdenadas[i]; // Intercambiar los elementos
                    cadenasOrdenadas[i] = cadenasOrdenadas[j]; // Mover el mayor a la posición i
                    cadenasOrdenadas[j] = temp; // Mover el menor a la posición j
                }
            }
        }
        print("Cadenas de texto ordenadas por longitud (de mayor a menor):");
        int i = 0;
        for (String cadena : cadenasOrdenadas) { // bucle para mostrar los elementos del arreglo ordenado con su longitud
            if (cadena != null) { // Saltar los elementos null
                i++;
                print(i + " - " + cadena + " (Longitud: " + cadena.length() + ")");
            }
        }
    }

    /**
     * Ver el arreglo completo con sus indices
     * @param cadenas Arreglo de cadenas de texto
     */
    public static void mostrarArreglo(String[] cadenas) {
        print("Contenido del arreglo de cadenas:");
        for (int i = 0; i < cadenas.length; i++) { // Bucle para mostrar los elementos del arreglo con su índice
            if (cadenas[i] != null) { // Si no es null, mostrar la cadena
                print("Índice " + i + ": " + cadenas[i]);
            } else { // Si es null, indicar que está vacío
                print("Índice " + i + ": [vacío]");
            }
        }
    }

    /**
     * Buscar una cadena de texto en el arreglo (por contenido)
     * @param cadenas Arreglo de cadenas de texto
     */
    public static void buscarCadena(String[] cadenas) {
        String cadenaBuscada = scann("Ingrese la cadena de texto a buscar: ", "");
        limpiarConsola();
        boolean encontrada = false; // Variable para indicar si se encontró la cadena
        for (int i = 0; i < cadenas.length; i++) { // Bucle para buscar la cadena en el arreglo
            if (cadenas[i] != null && cadenas[i].equalsIgnoreCase(cadenaBuscada)) { // Comparar ignorando mayúsculas/minúsculas
                print("Cadena encontrada en el índice " + i + ": " + cadenas[i]);
                encontrada = true; // Indicar que se encontró la cadena
            }
        }
        if (!encontrada) { // Si no se encontró la cadena, mostrar mensaje
            print("Cadena no encontrada en el arreglo.");
        }
    }

    /**
     * Eliminar una cadena de texto del arreglo (establecer a null)
     * @param cadenas Arreglo de cadenas de texto
     */
    public static void eliminarCadena(String[] cadenas) {
        int indice = (int) scann("Ingrese el índice (0-9) de la cadena que desea eliminar: ", 0);
        while (indice < 0 || indice > 9) { // Validar el índice
            print("Índice fuera de rango. Debe estar entre 0 y 9.");
            indice = (int) scann("Ingrese el índice (0-9) donde desea insertar/modificar la cadena: ", 0);
        }
        limpiarConsola();
        if (cadenas[indice] != null) { // Si no es null, eliminar la cadena (establecer a null)
            print("Cadena eliminada del índice " + indice + ": " + cadenas[indice]);
            cadenas[indice] = null;
        } else { // Si es null, indicar que no hay cadena para eliminar
            print("No hay ninguna cadena en el índice " + indice + " para eliminar.");
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
}
