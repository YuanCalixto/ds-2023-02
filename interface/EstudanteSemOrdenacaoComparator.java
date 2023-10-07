package org.example;

import java.util.Comparator;

public class EstudanteSemOrdenacaoComparator implements Comparator<EstudanteSemOrdenacao> {
    @Override
    public int compare(EstudanteSemOrdenacao estudante1, EstudanteSemOrdenacao estudante2) {

        int nomeComparison = estudante1.getNome().compareTo(estudante2.getNome());
        if (nomeComparison != 0) {
            return nomeComparison;
        }

        int sobrenomeComparison = estudante1.getSobrenome().compareTo(estudante2.getSobrenome());
        if (sobrenomeComparison != 0) {
            return sobrenomeComparison;
        }

        int idadeComparison = Integer.compare(estudante1.getIdade(), estudante2.getIdade());
        if (idadeComparison != 0) {
            return idadeComparison;
        }

        int notaComparison = Double.compare(estudante1.getNota(), estudante2.getNota());
        if (notaComparison != 0) {
            return notaComparison;
        }

        return Double.compare(estudante1.getMedia(), estudante2.getMedia());
    }
}
