import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
    private Queue<String> colaExamenes;

    public BufferExamenes() {
        colaExamenes = new LinkedList<String>();
    }

    public synchronized void fabricarNuevoExamen(String codigo) {
        // Añadir el código a la cola y notificar a los consumidores
        colaExamenes.add(codigo);
        notify(); // Despertar hilos que estén esperando un examen
    }

    public synchronized String consumirExamen() {
        while (colaExamenes.isEmpty()) {
            try {
                wait(); // Esperar hasta que haya un examen disponible
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return colaExamenes.poll(); // Sacar y retornar el examen
    }
}
