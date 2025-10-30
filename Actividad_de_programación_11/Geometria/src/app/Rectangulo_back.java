package src.app; // PAQUETE: src.app

/**
 * Clase Rectangulo_back que hereda de Cuadrado_back para calcular área y perímetro de un rectángulo.
 */
public class Rectangulo_back extends Cuadrado_back {

    /**
     * Constructor por defecto que inicializa los lados del rectángulo a 0.
     */
    public Rectangulo_back() {
        super.setArista(0);
        super.setArista2(0);
    }

    /**
     * Metodo para obtener el perímetro del rectángulo.
     * @return El perímetro del rectángulo.
     */
    @Override
    public float getPerimetro() {
        return (super.getArista() * 2) + (super.getArista2() * 2);
    }

    /**
     * Metodo para obtener el área del rectángulo.
     * @return El área del rectángulo.
     */
    @Override
    public float getArea() {
        return (super.getArista() * super.getArista2());
    }

    /**
     * Metodo para limpiar los atributos del rectángulo.
     */
    @Override
    public void clearAttributes() {
        super.setArista(0);
        super.setArista2(0);
    }
}
