/**
 * @author Equipo: A
 * @since 22 de Octubre de 2025
 * @version 1.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 10 - Conversor de sistemas numéricos: decimal, binario, octal y hexadecimal.
 */

package lib; // paquete de la clase

/**
 * Clase para convertir números entre diferentes sistemas numéricos
 */
public class Conversor_unidiades_back {
    String entrada;
    private String conversion;
    private String error;
    private boolean estado_error;
    private long valorNumerico;

    /**
     * Limpia los valores internos
     */
    public void clear() {
        this.entrada = "";
        this.conversion = "";
        this.error = "";
        this.estado_error = false;
        this.valorNumerico = 0;
    }

    /**
     * Constructor por defecto
     */
    public Conversor_unidiades_back() {
        clear();
    }
    
    /**
     * Metodo para obtener la entrada
     * @return la entrada
     */
    public String getEntrada() {
        return entrada;
    }

    /**
     * Metodo para obtener la conversion
     * @return la conversion
     */
    public String getConversion() {
        return conversion;
    }

    /**
     * Metodo para obtener el mensaje de error
     * @return el mensaje de error
     */
    public String getError() {
        return error;
    }

    /**
     * Metodo para obtener el estado de error
     * @return el estado de error
     */
    public boolean getEstadoError() {
        return estado_error;
    }

    /**
     * Metodo para obtener el valor numerico
     * @return el valor numerico
     */
    public long getValorNumerico() {
        return valorNumerico;
    }

    /**
     * Metodo para establecer la entrada
     * @param entrada la entrada a establecer
     */
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }
    
    /**
     * Convierte un número de un sistema numérico a otro
     * @param valor Valor a convertir (como String)
     * @param baseOrigen Base del sistema origen (2, 8, 10, 16)
     * @param baseDestino Base del sistema destino (2, 8, 10, 16)
     * @return true si la conversión fue exitosa, false en caso contrario
     */
    public boolean convertirSistemaNumericos(String valor, int baseOrigen, int baseDestino) {
        clear();
        this.entrada = valor + " (base " + baseOrigen + ")"; // Guardar entrada
        try { // Validar bases
            if (baseOrigen < 2 || baseOrigen > 16 || baseDestino < 2 || baseDestino > 16) { // Si las bases no son válidas
                this.error = "Las bases deben estar entre 2 y 16";
                this.estado_error = true;
                return false;
            }
            int longitudMaxima = calcularLongitudMaxima(baseOrigen); // Calcular longitud máxima permitida
            if (valor.length() > longitudMaxima) { // Validar longitud del número según la base
                this.error = "El número es demasiado largo. Máximo permitido: " + longitudMaxima + " dígitos en base " + baseOrigen;
                this.estado_error = true;
                return false;
            }
            // PASO 1: Convertir de baseOrigen a decimal usando operaciones matemáticas
            long decimal = convertirADecimal(valor, baseOrigen);
            this.valorNumerico = decimal;
            // PASO 2: Convertir de decimal a baseDestino usando operaciones matemáticas
            String resultado = convertirDesdeDecimal(decimal, baseDestino);
            this.conversion = resultado + " (base " + baseDestino + ")";
            return true; // Conversión exitosa
        } catch (NumberFormatException e) { // Capturar errores de formato
            this.error = "Formato de número no válido para la base especificada: " + e.getMessage();
            this.estado_error = true;
            return false;
        } catch (Exception e) { // Capturar cualquier otro error inesperado
            this.error = "Error en la conversión: " + e.getMessage();
            this.estado_error = true;
            return false;
        }
    }
    
    /**
     * Convierte un número de cualquier base a decimal usando operaciones matemáticas
     * @param valor Valor en la base original
     * @param base Base del número (2-16)
     * @return Valor decimal (long)
     */
    private long convertirADecimal(String valor, int base) throws NumberFormatException {
        valor = valor.toUpperCase(); // Normalizar a mayúsculas para hexadecimal
        long resultado = 0;
        long potencia = 1; // Representa base^0, base^1, base^2, etc.
        for (int i = valor.length() - 1; i >= 0; i--) { // Recorrer el número de derecha a izquierda
            char digito = valor.charAt(i);
            int valorDigito = obtenerValorDigito(digito);
            if (valorDigito >= base || valorDigito < 0) { // Validar que el dígito sea válido para la base
                throw new NumberFormatException("Dígito '" + digito + "' no válido para base " + base);
            }
            // Sumar: valor_digito * base^posicion
            resultado = resultado + (valorDigito * potencia);
            // Actualizar la potencia para la siguiente posición
            potencia = potencia * base;
        }
        return resultado;
    }
    
    /**
     * Convierte un número decimal a cualquier base usando operaciones matemáticas
     * @param decimal Número decimal
     * @param base Base destino (2-16)
     * @return String con el número en la base destino
     */
    private String convertirDesdeDecimal(long decimal, int base) {
        if (decimal == 0) { // Caso especial: si el número es 0
            return "0";
        }
        // Se utiliza un StringBuilder para construir el resultado
        StringBuilder resultado = new StringBuilder();
        long numero = decimal;
        while (numero > 0) { // Algoritmo de división sucesiva
            long residuo = numero % base; // Obtener el residuo
            char digito = obtenerCaracterDigito((int)residuo);
            resultado.insert(0, digito); // Insertar al inicio
            numero = numero / base; // División entera
        }
        return resultado.toString(); // Devolver el resultado como String
    }
    
    /**
     * Obtiene el valor numérico de un carácter (0-9, A-F)
     * @param c Carácter a convertir
     * @return Valor numérico (0-15)
     */
    private int obtenerValorDigito(char c) {
        if (c >= '0' && c <= '9') { // Validar dígitos '0'-'9'
            return c - '0'; // Convertir '0'-'9' a 0-9
        } else if (c >= 'A' && c <= 'F') {
            return 10 + (c - 'A'); // Convertir 'A'-'F' a 10-15
        } else {
            return -1; // Dígito no válido
        }
    }
    
    /**
     * Obtiene el carácter correspondiente a un valor numérico (0-15)
     * @param valor Valor numérico (0-15)
     * @return Carácter correspondiente ('0'-'9', 'A'-'F')
     */
    private char obtenerCaracterDigito(int valor) {
        if (valor >= 0 && valor <= 9) { // Validar 0-9
            return (char)('0' + valor); // Convertir 0-9 a '0'-'9'
        } else if (valor >= 10 && valor <= 15) { // Validar 10-15
            return (char)('A' + (valor - 10)); // Convertir 10-15 a 'A'-'F'
        } else { 
            return '?'; // Valor no válido
        }
    }
    
    /**
     * Calcula la longitud máxima permitida según la base para evitar overflow de Long
     * @param base Base del sistema numérico
     * @return Longitud máxima de dígitos permitidos
     */
    private int calcularLongitudMaxima(int base) {
        switch (base) { // Long.MAX_VALUE = 9,223,372,036,854,775,807
            case 2:  return 63;  // Binario: 63 bits
            case 8:  return 21;  // Octal: ~21 dígitos
            case 10: return 18;  // Decimal: 18 dígitos
            case 16: return 15;  // Hexadecimal: ~15 dígitos
            default: return 15;  // Por defecto, valor conservador
        }
    }
    
    /**
     * Valida si una cadena es un número válido para una base específica
     * @param texto Texto a validar
     * @param base Base del sistema numérico (2, 8, 10, 16)
     * @return true si es un número válido, false en caso contrario
     */
    public boolean validarNumeroEnBase(String texto, int base) {
        try {
            int longitudMaxima = calcularLongitudMaxima(base);
            if (texto.length() > longitudMaxima) { // Validar longitud del número según la base
                return false; // Número demasiado largo
            }
            // Usar nuestro algoritmo para validar
            convertirADecimal(texto, base);
            return true;
        } catch (NumberFormatException e) { // Capturar errores de formato
            return false;
        }
    }
}
