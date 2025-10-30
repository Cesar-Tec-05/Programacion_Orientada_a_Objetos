package src.app; // PAQUETE: src.app

/**
 * Clase abstracta Figuras_back que define los métodos y atributos básicos para las figuras geométricas.
 */
public abstract class Figuras_back {
    
    // Atributos heredados por las figuras geométricas
    private float arista;
    private float arista2;
    private float arista3;

    /**
     * Metodo abstracto para obtener el perímetro de la figura geométrica.
     */
    public abstract float getPerimetro();

    /**
     * Metodo abstracto para obtener el área de la figura geométrica.
     */
    public abstract float getArea();

    /**
     * Metodo para obtener la longitud de la arista de la figura geométrica.
     * @return La longitud de la arista.
     */
    public float getArista() {
        return arista;
    }

    /**
     * Metodo para obtener la longitud de la segunda arista de la figura geométrica.
     * @return La longitud de la segunda arista.
     */
    public float getArista2() {
        return arista2;
    }

    /**
     * Metodo para obtener la longitud de la tercera arista de la figura geométrica.
     * @return La longitud de la tercera arista.
     */
    public float getArista3() {
        return arista3;
    }

    /**
     * Metodo para establecer la longitud de la arista de la figura geométrica.
     * @param arista La longitud de la arista.
     */
    public void setArista(float arista) {
        this.arista = arista;
    }

    /**
     * Metodo para establecer la longitud de la segunda arista de la figura geométrica.
     * @param arista2 La longitud de la segunda arista.
     */
    public void setArista2(float arista2) {
        this.arista2 = arista2;
    }

    /**
     * Metodo para establecer la longitud de la tercera arista de la figura geométrica.
     * @param arista3 La longitud de la tercera arista.
     */
    public void setArista3(float arista3) {
        this.arista3 = arista3;
    }

    /**
     * Metodo abstracto para limpiar los atributos de la figura geométrica.
     */
    public abstract void clearAttributes();
}
