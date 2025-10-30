package src.app; // PAQUETE: src.app

import static java.lang.Math.*; // Importar librería matemática

/**
 * Clase Circulo_back que hereda de Figuras_back para calcular área y perímetro de un círculo.
 */
public class Circulo_back extends Figuras_back {

    protected final float pi = (float) Math.PI; // Constante pi

    /**
     * Constructor por defecto que inicializa el radio del círculo a 0.
     */
    public Circulo_back() {
        super.setArista(0);
    }

    /**
     * Metodo para obtener el perímetro del círculo.
     * @return El perímetro del círculo.
     */
    @Override
    public float getPerimetro() {
        return (this.pi * (super.getArista() * 2));
    }

    /**
     * Metodo para obtener el área del círculo.
     * @return El área del círculo.
     */
    @Override
    public float getArea() {
        return (this.pi * (float) (Math.pow(super.getArista(), 2)));
    }

    /**
     * Metodo para limpiar los atributos del círculo.
     */
    @Override
    public void clearAttributes() {
        super.setArista(0);
    }
}
