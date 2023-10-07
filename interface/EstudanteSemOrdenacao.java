package org.example;

public class EstudanteSemOrdenacao {
    private String nome;
    private String sobrenome;
    private int idade;
    private double nota;
    private double media;

    public EstudanteSemOrdenacao(String nome, String sobrenome, int idade, double nota, double media) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.nota = nota;
        this.media = media;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}
