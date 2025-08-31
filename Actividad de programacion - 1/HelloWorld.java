// CESAR DE JESUS BECERRA VERA 
// PROGRAMACION ORIENTADA A OBJETOS
// 25 AGOSTO DE 2025
// DESCRIPCION: PROGRAMA QUE IMPRIME "HOLA MUNDO"
/* Clase se debe llamar igual que el archivo */

/**
 * Clase principal del programa
 */
public class HelloWorld {

    /**
     * Este comentario es de documentación.
    */

    /**
     * Función principal del programa
     * @param args
     */
    public static void main(String[] args) { // inicio de la función main
        System.out.println("Hola, Mundo!");
        imprimir("Soy Cesar de Jesus Becerra Vera");
        imprimir(20);
    } // fin de la función main

    // metodos

    /*
     * Metodo para imprimir un dato tipo string
     */
    public static void imprimir (String mensaje) { // inicio del método imprimir
        System.out.println(mensaje);
    } // fin del método imprimir

    /*
     * Metodo para imprimir un dato tipo numerico
     */
    public static void imprimir (Number mensaje) { // inicio del método imprimir
        System.out.println(mensaje);
    } // fin del método imprimir
} //  fin de la clase
