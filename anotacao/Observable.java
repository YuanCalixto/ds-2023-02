import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Observable {
    private List<Consumer<String>> observers = new ArrayList<>();

    public void addObserver(Consumer<String> observer) {
        observers.add(observer);
    }

    public void notifyObservers(String observado) {
        observers.forEach(observer -> observer.accept(observado));
    }
}
