package src.app; // PAQUETE: src.app

/**
 * Clase Elipse_back que hereda de Circulo_back para calcular área y perímetro de una elipse.
 */
public class Elipse_back extends Circulo_back {

    /**
     * Constructor por defecto que inicializa los semi-ejes de la elipse a 0.
     */
    public Elipse_back() {
        super.setArista(0);
        super.setArista2(0);
    }

    /**
     * Metodo para obtener el perímetro de la elipse.
     * @return El perímetro de la elipse.
     */
    @Override
    public float getPerimetro() {
        return (super.pi * (3 * (super.getArista() + super.getArista2()) - (float) Math.sqrt((3 * super.getArista() + super.getArista2()) * (super.getArista() + 3 * super.getArista2()))));
    }

    /**
     * Metodo para obtener el área de la elipse.
     * @return El área de la elipse.
     */
    @Override
    public float getArea() {
        return (super.pi * (super.getArista() * super.getArista2()));
    }

    /**
     * Metodo para limpiar los atributos de la elipse.
     */
    @Override
    public void clearAttributes() {
        super.setArista(0);
        super.setArista2(0);
    }
}
