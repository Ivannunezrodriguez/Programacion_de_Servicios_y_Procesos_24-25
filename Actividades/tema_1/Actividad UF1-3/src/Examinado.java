import java.io.PrintWriter;
import java.util.Random;

public class Examinado implements Runnable {

    private Thread hilo;
    private BufferExamenes buffer;
    private PrintWriter salida;

    public Examinado(String alumno, BufferExamenes generador, PrintWriter salida) {
        this.hilo = new Thread(this, alumno);
        this.buffer = generador;
        this.salida = salida;
        this.hilo.start();
    }

    @Override
    public void run() {
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
            generarRespuestas(codigoExamen);
        } else {
            salida.println("Agotado tiempo de espera y no hay más exámenes para " + this.hilo.getName());
        }
    }

    private void generarRespuestas(String codigoExamen) {
        Random rand = new Random();
        String[] respuestas = {"A", "B", "C", "D", "-"};

        for (int i = 1; i <= 10; i++) {
            String respuesta = respuestas[rand.nextInt(respuestas.length)];
            salida.println(codigoExamen + ";" + this.hilo.getName() + "; Pregunta " + i + ";" + respuesta);
        }
    }
    }

