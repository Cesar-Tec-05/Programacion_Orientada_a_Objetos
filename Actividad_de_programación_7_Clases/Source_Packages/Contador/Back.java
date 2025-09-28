package Contador;

/**
 * Clase Back que implementa la funcionalidad del contador
 */
public class Back {
    /**
     * Atributo que almacena el valor del contador
     */
    int count;

    /**
     * Constructor que inicializa el contador en x
     * @param x Valor inicial del contador
     */
    public Back(int x) {
        this.count = x;
    }
    
    /**
     * Incrementa el contador en x
     * @param x Valor a incrementar
     */
    public void incrementar(int x) {
        this.count += x;
    }

    /**
     * Incrementa el contador en 1
     */
    public void incrementar() {
        this.count++;
    }

    /**
     * Decrementa el contador en 1
     */
    public void reiniciar() {
        this.count = 0;
    }

    /**
     * Reinicia el contador en x
     * @param x Valor a reiniciar
     */
    public void reiniciar(int x) {
        this.count = x;
    }

    /**
     * Decrementa el contador en 1
     */
    public void decrementar() {
        this.count--;
    }

    /**
     * Muestra el valor actual del contador
     * @return Cadena con el valor del contador
     */
    public String mostrar() {
        return "Contador: " + count;
    }
}
