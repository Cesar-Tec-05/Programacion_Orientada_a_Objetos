package src.back_geometria; // PAQUETE: src.app

/**
 * Clase Cuadrado_back que hereda de Figuras_back para calcular área y perímetro de un cuadrado.
 */
public class Cuadrado_back extends Figuras_back {

    /**
     * Constructor por defecto que inicializa el lado del cuadrado a 0.
     */
    public Cuadrado_back() {
        super.setArista(0);
    }

    /**
     * Metodo para obtener el perímetro del cuadrado.
     * @return El perímetro del cuadrado.
     */
    @Override
    public float getPerimetro() {
        return (super.getArista() * 4);
    }

    /**
     * Metodo para obtener el área del cuadrado.
     * @return El área del cuadrado.
     */
    @Override
    public float getArea() {
        return (float) (Math.pow(super.getArista(), 2));
    }

    /**
     * Metodo para limpiar los atributos del cuadrado.
     */
    @Override
    public void clearAttributes() {
        super.setArista(0);
    }
}
