package org.example;

public class Main {
    public static void main(String[] args) {
        double number = 16.0;
        double epsilon = 0.001;

        double result = RaizQuadrada.calculateSquareRoot(number, epsilon);

        System.out.println("A raiz quadrada de " + number + " é aproximadamente: " + result);
    }
}
