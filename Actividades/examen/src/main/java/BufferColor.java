import java.util.LinkedList;
import java.util.Queue;

class BufferColor {
    private Queue<String> cola;

    public BufferColor() {
        cola = new LinkedList<>();
    }

    public synchronized void poner(String color) {
        cola.add(color);
        notify();
    }

    public synchronized String sacar() {
        while (cola.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return cola.remove();
    }
}