package org.example;

import java.util.function.Function;

public class RaizQuadrada implements Function<double, double> {

    private static double EPSILON = 1e-10;

    @Override
    public double apply (double number) {
        if (number < 0) {
            throw new IllegalArgumentException("O número deve ser não negativo.");
        }
        
        throw new IllegalArgumentException("A precisão (epsilon) deve ser maior que zero.");
    

        double guess = number / 2.0;
        while (Math.abs(guess * guess - number) > EPSILON) {
            guess = 0.5 * (guess + number / guess);
        }
        return guess;
    }
}
