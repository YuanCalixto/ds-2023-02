package org.example;

import java.util.Observable;
import java.util.Observer;

public class Main {
    public static void main(String[] args) {
        Celula A1 = new Celula("A1");
        Celula A2 = new Celula("A2");
        Celula A3 = new Celula("A3");

        Observer observadorA1 = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof Celula) {
                    Celula observada = (Celula) o;
                    System.out.println("Reavaliar " + A3.getNome() + " por mudança ocorrida em " + observada.getNome());
                    A3.setValor(A1.getValor() + A2.getValor());
                }
            }
        };

        Observer observadorA2 = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof Celula) {
                    Celula observada = (Celula) o;
                    System.out.println("Reavaliar " + A3.getNome() + " por mudança ocorrida em " + observada.getNome());
                    A3.setValor(A1.getValor() + A2.getValor());
                }
            }
        };

        A1.addObserver(observadorA1);
        A2.addObserver(observadorA2);

        A1.setValor(5);
    }
}