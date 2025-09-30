package contador;
/**
 * @author Cesar de Jesus Becerra Vera
 * @since 27 de Septiembre de 2025
 * @version 1.0
 * PAQUETE: Contador
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 7, creación de clases y objetos y importaciones.
 */

/**
 * Importar la clase Scanner para entrada de datos
 */
import java.util.Scanner;

/**
 * Clase principal Main que contiene el método main y métodos auxiliares
 */
public class Main {
    /**
     * Método principal que ejecuta el programa
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int valorInicial = 0; // Valor inicial del contador
        print("====== Programa Contador ======");
        print("Quiere iniciar con un valor personalizado?");
        int opcionNumero = scann("Ingrese 1 para Sí, 0 para No:");

        if (opcionNumero == 1) { // Iniciar con valor personalizado
            valorInicial = scann("Ingrese el valor inicial del contador:");
        }

        Back contador = new Back(valorInicial); // Crear objeto contador con valor inicial o 0
        print("Contador iniciado.");

        while (true) { // Bucle principal del menú
            esperarTecla(); 
            limpiarConsola(); 
            print("--- Operaciones disponibles ---");
            print("1. Incrementar");
            print("2. Incrementar en X");
            print("3. Decrementar");
            print("4. Reiniciar");
            print("5. Reiniciar en X");
            print("6. Mostrar valor actual");
            print("7. Serie Fibonacci");
            print("8. Salir");
            int opcion = scann("Seleccione una opción (1-7):");

            while (opcion < 1 || opcion > 8) { // Validar opción
                print("Opción inválida. Por favor, intente de nuevo.");
                opcion = scann("Seleccione una opción (1-7):");
            }

            switch (opcion) { // Ejecutar opción seleccionada
                case 1:
                    contador.incrementar();
                    print("Contador incrementado en 1.");
                    break;
                case 2:
                    int incremento = scann("Ingrese el valor a incrementar:");
                    contador.incrementar(incremento);
                    print("Contador incrementado en " + incremento + ".");
                    break;
                case 3:
                    contador.decrementar();
                    print("Contador decrementado en 1.");
                    break;
                case 4:
                    contador.reiniciar();
                    print("Contador reiniciado a 0.");
                    break;
                case 5:
                    int nuevoValor = scann("Ingrese el nuevo valor para reiniciar:");
                    contador.reiniciar(nuevoValor);
                    print("Contador reiniciado a " + nuevoValor + ".");
                    break;
                case 6:
                    print(contador.mostrar());
                    break;
                case 7:
                    int n = scann("Ingrese el número de términos de la serie Fibonacci a generar:");
                    if (n <= 0) {
                        print("El número debe ser mayor a 0.");
                        break;
                    }
                    print("Serie Fibonacci de " + n + " términos:");
                    if (n >= 1) {
                        // Primer término (0)
                        contador.reiniciar(0);
                        print("Término 1: " + contador.count);
                    }
                    if (n >= 2) {
                        // Segundo término (1)
                        contador.reiniciar(1);
                        print("Término 2: " + contador.count);
                    }
                    // Términos restantes (suma de los dos anteriores)
                    int anterior = 0; // F(n-2)
                    int actual = 1;   // F(n-1)
                    for (int i = 3; i <= n; i++) {
                        int siguiente = anterior + actual; // F(n) = F(n-1) + F(n-2)
                        contador.reiniciar(siguiente);
                        print("Término " + i + ": " + contador.count);
                        anterior = actual;
                        actual = siguiente;
                    }
                    break;
                default:
                    print("Saliendo del programa. ¡Hasta luego!");
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
}
