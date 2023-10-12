package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

class Celula extends Observable {
        private String nome;
    private int valor;
    private List<Observer> observadores = new ArrayList<>();

    public Celula(String nome) {
        this.nome = nome;
        this.valor = 0;
    }

    public void setValor(int novoValor) {
        if (novoValor != valor) {
            valor = novoValor;
            notificarObservadores();
        }
    }

    public void adicionarObservador(Observer observador) {
        observadores.add(observador);
    }

    private void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.update(this, null);
        }
    }

    public int getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}