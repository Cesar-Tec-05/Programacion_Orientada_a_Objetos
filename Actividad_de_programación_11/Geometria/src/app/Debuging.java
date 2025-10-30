package src.app; // PAQUETE: src.app

/**
 * Clase Debuging para probar las clases de figuras geométricas.
 */
public class Debuging {
    /**
     * Método principal para ejecutar las pruebas.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        
        Circulo_back circulo = new Circulo_back();
        circulo.setArista(100);
        System.out.println("Círculo:");
        System.out.println("Radio: " + circulo.getArista());
        System.out.println("Perímetro: " + circulo.getPerimetro());
        System.out.println("Área: " + circulo.getArea());

        Elipse_back elipse = new Elipse_back();
        elipse.setArista(50);
        elipse.setArista2(51);
        System.out.println("\nElipse:");
        System.out.println("Semi-eje mayor: " + elipse.getArista());
        System.out.println("Semi-eje menor: " + elipse.getArista2());
        System.out.println("Perímetro: " + elipse.getPerimetro());
        System.out.println("Área: " + elipse.getArea());

        Triangulo_back triangulo = new Triangulo_back();
        triangulo.setArista(30);
        triangulo.setArista2(40);
        triangulo.setArista3(50);
        triangulo.setBase(40);
        triangulo.setAltura(30);
        System.out.println("\nTriángulo:");
        System.out.println("Lado 1: " + triangulo.getArista());
        System.out.println("Lado 2: " + triangulo.getArista2());
        System.out.println("Lado 3: " + triangulo.getArista3());
        System.out.println("Base: " + triangulo.getBase());
        System.out.println("Altura: " + triangulo.getAltura());
        System.out.println("Perímetro: " + triangulo.getPerimetro());
        System.out.println("Área: " + triangulo.getArea());

        Cuadrado_back cuadrado = new Cuadrado_back();
        cuadrado.setArista(60);
        System.out.println("\nCuadrado:");
        System.out.println("Lado: " + cuadrado.getArista());
        System.out.println("Perímetro: " + cuadrado.getPerimetro());
        System.out.println("Área: " + cuadrado.getArea());

        Rectangulo_back rectangulo = new Rectangulo_back();
        rectangulo.setArista(70);
        rectangulo.setArista2(80);
        System.out.println("\nRectángulo:");
        System.out.println("Base: " + rectangulo.getArista());
        System.out.println("Altura: " + rectangulo.getArista2());
        System.out.println("Perímetro: " + rectangulo.getPerimetro());
        System.out.println("Área: " + rectangulo.getArea());

        Romboide_back romboide = new Romboide_back();
        romboide.setArista(90);
        romboide.setArista2(100);
        romboide.setLado_oblicuo(110);
        System.out.println("\nRomboide:");
        System.out.println("Base: " + romboide.getArista());
        System.out.println("Altura: " + romboide.getArista2());
        System.out.println("Lado oblicuo: " + romboide.getLado_oblicuo());
        System.out.println("Perímetro: " + romboide.getPerimetro());
        System.out.println("Área: " + romboide.getArea());

        Poligono_regular_back poligono = new Poligono_regular_back();
        poligono.setArista(20);
        poligono.setNumLados(5);
        System.out.println("\nPolígono Regular:");
        System.out.println("Lado: " + poligono.getArista());
        System.out.println("Número de lados: " + poligono.getNumLados());
        System.out.println("Apotema: " + poligono.getApotema());
        System.out.println("Perímetro: " + poligono.getPerimetro());
        System.out.println("Área: " + poligono.getArea());
    }
}
