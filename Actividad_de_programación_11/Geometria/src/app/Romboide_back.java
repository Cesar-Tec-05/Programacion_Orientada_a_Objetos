package src.app; // PAQUETE: src.app

/**
 * Clase Romboide_back que hereda de Cuadrado_back para calcular área y perímetro de un romboide.
 */
public class Romboide_back extends Cuadrado_back {

    private float lado_oblicuo; // Longitud del lado oblicuo del romboide

    /**
     * Constructor por defecto que inicializa los lados del romboide a 0.
     */
    public Romboide_back() {
        super.setArista(0);
        super.setArista2(0);
        this.setLado_oblicuo(0);
    }

    /**
     * Metodo para obtener la longitud del lado oblicuo del romboide.
     * @return La longitud del lado oblicuo.
     */
    public float getLado_oblicuo() {
        return lado_oblicuo;
    }

    /**
     * Metodo para establecer la longitud del lado oblicuo del romboide.
     * @param lado_oblicuo La longitud del lado oblicuo.
     */
    public void setLado_oblicuo(float lado_oblicuo) {
        this.lado_oblicuo = lado_oblicuo;
    }

    /**
     * Metodo para obtener el perímetro del romboide.
     * @return El perímetro del romboide.
     */
    @Override
    public float getPerimetro() {
        return (2 * (super.getArista() + this.lado_oblicuo));
    }

    /**
     * Metodo para obtener el área del romboide.
     * @return El área del romboide.
     */
    @Override
    public float getArea() {
        return (super.getArista() * super.getArista2());
    }

    /**
     * Metodo para limpiar los atributos del romboide.
     */
    @Override
    public void clearAttributes() {
        super.setArista(0);
        super.setArista2(0);
        this.setLado_oblicuo(0);
    }

}

