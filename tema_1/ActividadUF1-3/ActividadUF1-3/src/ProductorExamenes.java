import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {

    private BufferExamenes buffer;
    private static int numeroExamen = 0;
    private Thread hilo;
    private PrintWriter salida;

    public ProductorExamenes(BufferExamenes buffer, PrintWriter salida) {
        this.buffer = buffer;
        this.salida = salida;

        numeroExamen++;
        this.hilo = new Thread(this, "E" + numeroExamen);

        this.hilo.start();
    }

    @Override
    public void run() {
        int aa = LocalDateTime.now().getYear();
        String codigo = this.hilo.getName() + "-" + aa;

        buffer.fabricarNuevoExamen(codigo);

        salida.println("Producido examen " + codigo);
    }
}
