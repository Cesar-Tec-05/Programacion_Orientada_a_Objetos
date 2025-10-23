package src.app;

/**
 * Debugging de la clase Conversor_unidiades_back
 */

import src.app.Conversor_unidiades_back;

public class Debuging {
    static Conversor_unidiades_back conversor = new Conversor_unidiades_back();

    public static void main(String[] args) {
        System.out.println("----- Probando conversor de sistemas numéricos -----");
        conversor.clear();
        conversor.setEntrada("255"); // Simular entrada
        conversor.convertirSistemaNumericos(conversor.getEntrada(), 10, 2); // Convertir de decimal a binario
        System.out.println("Entrada antes de la conversión: " + conversor.getEntrada());
        System.out.println("Conversión a binario: " + conversor.getConversion());
        System.out.println("Estado de error después de la conversión: " + conversor.getEstadoError());
        conversor.clear();
        System.out.println("----- Probando otra conversión Valida-----");
        conversor.setEntrada("11111111000"); // Simular entrada
        conversor.convertirSistemaNumericos(conversor.getEntrada(), 2, 10); // Convertir de binario a decimal
        System.out.println("Entrada antes de la conversión: " + conversor.getEntrada());
        System.out.println("Conversión a decimal: " + conversor.getConversion());
        System.out.println("Estado de error después de la conversión: " + conversor.getEstadoError());
        conversor.clear();
        System.out.println("----- Probando conversión Decimal a octal -----");
        conversor.setEntrada("255"); // Simular entrada
        conversor.convertirSistemaNumericos(conversor.getEntrada(), 10, 8); // Convertir de decimal a octal
        System.out.println("Entrada antes de la conversión: " + conversor.getEntrada());
        System.out.println("Conversión a octal: " + conversor.getConversion());
        System.out.println("Estado de error después de la conversión: " + conversor.getEstadoError());
        conversor.clear();
        System.out.println("----- Probando entrada inválida -----");
        conversor.setEntrada("FFF93G"); // Simular entrada inválida
        conversor.convertirSistemaNumericos(conversor.getEntrada(), 16, 10); // Convertir de hexadecimal a decimal
        System.out.println("Entrada antes de la conversión: " + conversor.getEntrada());
        System.out.println("Conversión a decimal: " + conversor.getConversion());
        System.out.println("Estado de error después de la conversión: " + conversor.getEstadoError());
        System.out.println("Mensaje de error: " + conversor.getError());
    }
}
