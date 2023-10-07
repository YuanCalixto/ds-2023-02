package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<EstudanteSemOrdenacao> listaDeEstudantes = new ArrayList<>();

        listaDeEstudantes.add(new EstudanteSemOrdenacao("João", "Silva", 20, 8.5, 7.8));
        listaDeEstudantes.add(new EstudanteSemOrdenacao("Maria", "Santos", 22, 7.2, 6.9));
        listaDeEstudantes.add(new EstudanteSemOrdenacao("Pedro", "Ferreira", 19, 9.0, 8.7));

        Collections.sort(listaDeEstudantes, new EstudanteSemOrdenacaoComparator());

        System.out.println("Ordenado por nome:");
        for (EstudanteSemOrdenacao estudante : listaDeEstudantes) {
            System.out.println(estudante.getNome());
        }

        Collections.sort(listaDeEstudantes, new EstudanteSemOrdenacaoComparator());

        System.out.println("\nOrdenado por sobrenome:");
        for (EstudanteSemOrdenacao estudante : listaDeEstudantes) {
            System.out.println(estudante.getSobrenome());
        }

        Collections.sort(listaDeEstudantes, new EstudanteSemOrdenacaoComparator());

        System.out.println("\nOrdenado por nota:");
        for (EstudanteSemOrdenacao estudante : listaDeEstudantes) {
            System.out.println(estudante.getNota());
        }

        Collections.sort(listaDeEstudantes, new EstudanteSemOrdenacaoComparator());

        System.out.println("\nOrdenado por média:");
        for (EstudanteSemOrdenacao estudante : listaDeEstudantes) {
            System.out.println(estudante.getMedia());
        }
    }
}

