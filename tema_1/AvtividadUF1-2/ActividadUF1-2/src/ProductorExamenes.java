import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {
    private BufferExamenes buffer;
    private static int numeroExamen = 0;
    private Thread hilo;

    public ProductorExamenes(BufferExamenes buffer) {
        numeroExamen++;
        this.hilo = new Thread(this, "E" + numeroExamen); // Nombre del hilo
        this.buffer = buffer;
        hilo.start(); // Iniciar el hilo
    }

    @Override
    public synchronized void run() {
        int aa = LocalDateTime.now().getYear();
        String codigo = this.hilo.getName() + "-" + aa;

        buffer.fabricarNuevoExamen(codigo); // Añadir examen al buffer
        System.out.println("Producido examen " + codigo);
    }
}
