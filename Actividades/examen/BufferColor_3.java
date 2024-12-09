import java.util.LinkedList;
import java.util.Queue;

public class BufferColor {
    private Queue<Integer> cola;

    public BufferColor() {
        cola = new LinkedList<>();
    }

    public static String obtenerColor(Integer numero) {
        switch (numero) {
            case 1:
                return "GRIS";
            case 2:
                return "Verde";
            case 3:
                return "Amarillo";
            case 4:
                return "Azul";
            default:
                return "Desconocido";
        }
    }

    public synchronized void poner(Integer color) {
        cola.add(color);
        notify();
    }

    public synchronized Integer sacar() {
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
