public class Main {

    public static void main(String[] args) {

        Expressao expressao = new Soma(new Constante(5), new Multiplica(new Constante(2), new Constante(3)));

        float resultado = expressao.valor();

        System.out.println("O valor da expressão é: " + resultado);
    }
}