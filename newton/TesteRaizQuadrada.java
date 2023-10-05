package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TesteRaizQuadrada {

    @Test
    public void testCalculateSquareRoot() {
        // Teste com precisão de 0.001
        double result1 = RaizQuadrada.calculateSquareRoot(16, 0.001);
        assertEquals(4.0, result1, 0.001);

        // Teste com precisão de 0.0001
        double result2 = RaizQuadrada.calculateSquareRoot(25, 0.0001);
        assertEquals(5.0, result2, 0.0001);

        // Teste com precisão de 0.00001
        double result3 = RaizQuadrada.calculateSquareRoot(2, 0.00001);
        assertEquals(Math.sqrt(2), result3, 0.00001);
    }

    @Test
    public void testeNumeroNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            RaizQuadrada.calculateSquareRoot(-1, 0.001);
        });
    }

    @Test
    public void testeEpsilonNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            RaizQuadrada.calculateSquareRoot(9, -0.001);
        });
    }
}
