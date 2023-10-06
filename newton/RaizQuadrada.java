package org.example;

public class RaizQuadrada {

    private static double EPSILON = 1e-10
    
    public static double calculateSquareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("O número deve ser não negativo.");
        }
        if (epsilon <= 0) {
            throw new IllegalArgumentException("A precisão (epsilon) deve ser maior que zero.");
        }

        double guess = number / 2.0;
        while (Math.abs(guess * guess - number) > epsilon) {
            guess = 0.5 * (guess + number / guess);
        }
        return guess;
    }
}
