public class Main {
    public static void main(String[] args) {
        Observable observado = new Observable();

        observado.addObserver(observadoNome ->
                System.out.println("Observador notificado sobre o objeto observado: " + observadoNome)
        );

        observado.notifyObservers("carro");
    }
}
