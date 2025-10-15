package lib; // PAQUETE: src.app

/**
 * @author Cesar de Jesus Becerra Vera
 * @since 14 de Octubre de 2025
 * @version 2.0
 * PAQUETE: src.app
 * CENTRO UNIVERSITARIO DE LOS ALTOS / UNIVERSIDAD DE GUADALAJARA
 * INGENIERIA EN COMPUTACION / 3ER SEMESTRE
 * PROFESOR: Sergio Franco Casillas
 * DESCRIPCIÓN: Actividad de programación 9 - Calculadora básica
 */

/**
 * Clase que representa la lógica de la calculadora básica.
 */
public class Back_calculadora_basica {
    /**
     * Variables para almacenar el estado de la calculadora.
     */
    private double valorActual;
    private double valorAlmacenado;
    private String operacionActual;
    private String textoActual;
    private boolean esNuevaEntrada;
    private boolean tieneError;
    private String mensajeError;

    /**
     * Constructor que inicializa la calculadora por defecto.
     */
    public Back_calculadora_basica() {
        limpiar();
    }
    
    /**
     * Resetea la calculadora a su estado inicial.
     */
    public void limpiar() {
        this.valorActual = 0;
        this.valorAlmacenado = 0;
        this.operacionActual = null;
        this.textoActual = "0";
        this.esNuevaEntrada = true;
        this.tieneError = false;
        this.mensajeError = null;
    }
    
    /**
     * Agrega un dígito o punto decimal al valor actual.
     * @param digito El dígito o punto decimal a agregar (ej. "0"-"9" o ".").
     */
    public void agregarDigito(String digito) {
        if (tieneError) { // Si hay un error, resetear la calculadora.
            limpiar();
        }
        
        if (esNuevaEntrada) { // Si es una nueva entrada, reemplazar el número actual.
            if (digito.equals(".")) {
                textoActual = "0.";
            } else { // Evitar múltiples ceros a la izquierda
                textoActual = digito;
            }
            esNuevaEntrada = false;
        } else { // Continuar agregando al número actual.
            if (digito.equals(".") && textoActual.contains(".")) { // No permitir múltiples puntos decimales
                return;
            }
            if (textoActual.equals("0") && !digito.equals(".")) { // Reemplazar el cero inicial si no es un punto decimal
                textoActual = digito;
            } else { // Agregar el dígito al final
                textoActual += digito;
            }
        }
        
        try { // Actualizar el valor numérico actual
            valorActual = Double.parseDouble(textoActual);
        } catch (NumberFormatException e) { // Manejar error de formato numérico
            establecerError("Número inválido");
        }
    }
    
    /**
     * Establece la operación actual (+, -, *, /).
     * @param operacion La operación a establecer.
     */
    public void establecerOperacion(String operacion) {
        if (tieneError) { // Si hay un error, no hacer nada.
            return;
        }
        
        if (operacionActual != null && !esNuevaEntrada) { // Si ya hay una operación pendiente, calcular primero.
            calcular();
        } else { // Si no, almacenar el valor actual.
            valorAlmacenado = valorActual;
            // Actualizar el texto para mostrar el valor actual correctamente
            actualizarTextoActual();
        }

        // Establecer la nueva operación
        operacionActual = operacion;
        esNuevaEntrada = true;
    }
    
    /**
     * Realiza el cálculo basado en la operación actual.
     */
    public void calcular() {
        if (tieneError || operacionActual == null) { // Si hay un error o no hay operación, no hacer nada.
            return;
        }
        
        try { // Realizar la operación
            switch (operacionActual) { // Evaluar la operación actual
                case "+": // Suma
                    sumar();
                    break;
                case "-": // Resta
                    restar();
                    break;
                case "*": // Multiplicación
                    multiplicar();
                    break;
                case "/": // División
                    dividir();
                    break;
                default: // Operación desconocida
                    return;
            }
            
            // Actualizar el valor almacenado y restablecer la operación
            valorAlmacenado = valorActual;
            operacionActual = null;
            esNuevaEntrada = true;
            // Actualizar el texto para mostrar el resultado
            actualizarTextoActual();
            
        } catch (Exception e) { // Manejar cualquier otro error inesperado
            establecerError("Error de cálculo");
        }
    }
    
    /**
     * Realiza la operación de suma.
     */
    public void sumar() {
        valorActual = valorAlmacenado + valorActual;
    }
    
    /**
     * Realiza la operación de resta.
     */
    public void restar() {
        valorActual = valorAlmacenado - valorActual;
    }
    
    /**
     * Realiza la operación de multiplicación.
     */
    public void multiplicar() {
        valorActual = valorAlmacenado * valorActual;
    }
    
    /**
     * Realiza la operación de división.
     */
    public void dividir() {
        if (valorActual == 0) { // Manejar división entre cero, un error común
            establecerError("No se puede dividir entre cero");
            return;
        }
        valorActual = valorAlmacenado / valorActual;
    }
    
    /**
     * Cambia el signo del valor actual.
     */
    public void cambiarSigno() {
        if (tieneError) { // Si hay un error, no hacer nada.
            return;
        }
        
        // Cambiar el signo del valor actual
        valorActual = -valorActual;
        // Actualizar el texto para mostrar el nuevo valor
        actualizarTextoActual();
    }
    
    /**
     * Actualiza el texto actual basado en el valor numérico.
     */
    public void actualizarTextoActual() {
        if (valorActual == Math.floor(valorActual) && valorActual <= Long.MAX_VALUE && valorActual >= Long.MIN_VALUE) {
            // Si es un número entero, mostrarlo sin decimales
            textoActual = String.valueOf((long) valorActual);
        } else {
            // Si es decimal, mostrarlo con precisión limitada
            textoActual = String.valueOf(valorActual);
            if (textoActual.length() > 12) {
                textoActual = String.format("%.6g", valorActual);
            }
        }
    }
    
    /**
     * Establece el estado de error.
     * @param mensaje Mensaje de error a mostrar.
     */
    public void establecerError(String mensaje) { 
        // Establecer el estado de error
        tieneError = true;
        mensajeError = mensaje;
    }

    /**
     * Obtiene la operación actual.
     * @return La operación actual (+, -, *, /) o null si no hay ninguna.
     */
    public String obtenerOperacionActual() {
        return operacionActual;
    }
    
    /**
     * Indica si la calculadora está en estado de error.
     * @return true si hay un error, false en caso contrario.
     */
    public boolean tieneError() {
        return tieneError;
    }
    
    /**
     * Obtiene el mensaje de error actual.
     * @return Mensaje de error o null si no hay error.
     */
    public String obtenerMensajeError() {
        return mensajeError;
    }
    
    /**
     * Obtiene el valor numérico actual.
     * @return El valor numérico actual.
     */
    public double obtenerValorActual() {
        return valorActual;
    }
    
    /**
     * Obtiene el valor almacenado.
     * @return El valor almacenado.
     */
    public double obtenerValorAlmacenado() {
        return valorAlmacenado;
    }
    
    /**
     * Indica si la entrada actual es nueva (después de una operación o limpiar).
     * @return true si la entrada es nueva, false en caso contrario.
     */
    public boolean esNuevaEntrada() {
        return esNuevaEntrada;
    }
    
    /**
     * Establece un valor directamente.
     * @param valor El valor a establecer.
     */
    public void establecerValor(double valor) {
        valorActual = valor;
        actualizarTextoActual();
        esNuevaEntrada = false;
    }
    
    /**
     * Obtiene el texto actual tal como se muestra.
     * @return El texto actual.
     */
    public String obtenerTextoActual() {
        return textoActual;
    }
}
