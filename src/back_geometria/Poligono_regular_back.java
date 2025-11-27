package src.back_geometria; // PAQUETE: src.app

/**
 * Clase Poligono_regular_back que hereda de Figuras_back para calcular área y perímetro de un polígono regular.
 */
public class Poligono_regular_back extends Figuras_back {

    private float num_lados; // Número de lados del polígono regular

    /**
     * Constructor por defecto que inicializa el lado del polígono regular y el número de lados a 0.
     */
    public Poligono_regular_back() {
        super.setArista(0);
        this.setNumLados(0);
    }

    /**
     * Metodo para establecer el número de lados del polígono regular.
     * @param num_lados El número de lados del polígono regular.
     */
    public void setNumLados(float num_lados) {
        this.num_lados = num_lados;
    }

    /**
     * Metodo para obtener el número de lados del polígono regular.
     * @return El número de lados del polígono regular.
     */
    public float getNumLados() {
        return this.num_lados;
    }

    /**
     * Metodo para obtener la apotema del polígono regular.
     * @return La apotema del polígono regular.
     */
    public float getApotema() {
        return (super.getArista() / (float) (2 * Math.tan(Math.PI / this.num_lados)));
    }

    /**
     * Metodo para obtener el perímetro del polígono regular.
     * @return El perímetro del polígono regular.
     */
    @Override
    public float getPerimetro() {
        return (this.num_lados * super.getArista());
    }

    /**
     * Metodo para obtener el área del polígono regular.
     * @return El área del polígono regular.
     */
    @Override
    public float getArea() {
        return ((this.getPerimetro() * this.getApotema()) / 2);
    }

    /**
     * Metodo para limpiar los atributos del polígono regular.
     */
    @Override
    public void clearAttributes() {
        super.setArista(0);
        this.setNumLados(0);
    }
}
