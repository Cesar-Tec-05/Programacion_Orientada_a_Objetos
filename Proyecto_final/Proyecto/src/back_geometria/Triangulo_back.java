package src.back_geometria; // PAQUETE: src.app

public class Triangulo_back extends Figuras_back {

    // Atributos específicos del triángulo
    private float altura;
    private float base;

    /**
     * Constructor por defecto que inicializa los lados, altura y base del triángulo a 0.
     */
    public Triangulo_back() {
        super.setArista(0);
        super.setArista2(0);
        super.setArista3(0);
        this.setAltura(0);
        this.setBase(0);
    }

    /**
     * Metodo para obtener la altura del triángulo.
     * @return La altura del triángulo.
     */
    public float getAltura() {
        return altura;
    }

    /**
     * Metodo para obtener la base del triángulo.
     * @return La base del triángulo.
     */
    public float getBase() {
        return base;
    }

    /**
     * Metodo para establecer la altura del triángulo.
     * @param altura La altura del triángulo.
     */
    public void setAltura(float altura) {
        this.altura = altura;
    }

    /**
     * Metodo para establecer la base del triángulo.
     * @param base La base del triángulo.
     */
    public void setBase(float base) {
        this.base = base;
    }

    /**
     * Metodo para obtener el perímetro del triángulo.
     * @return El perímetro del triángulo.
     */
    @Override
    public float getPerimetro() {
        return (super.getArista() + super.getArista2() + super.getArista3());
    }

    /**
     * Metodo para obtener el área del triángulo.
     * @return El área del triángulo.
     */
    @Override
    public float getArea() {
        return (this.getBase() * this.getAltura()) / 2;
    }

    /**
     * Metodo para limpiar los atributos del triángulo.
     */
    @Override
    public void clearAttributes() {
        super.setArista(0);
        super.setArista2(0);
        super.setArista3(0);
        this.setAltura(0);
        this.setBase(0);
    }
}
