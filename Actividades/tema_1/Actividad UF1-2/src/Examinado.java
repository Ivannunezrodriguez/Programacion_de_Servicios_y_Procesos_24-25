import java.util.Random;

public class Examinado implements Runnable {
    private Thread hilo;
    private BufferExamenes buffer;

    public Examinado(String alumno, BufferExamenes generador) {
        this.hilo = new Thread(this, alumno); // Nombre del hilo con el nombre del alumno
        this.buffer = generador;
        hilo.start(); // Iniciar el hilo
    }

    @Override
    public synchronized void run() {
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
            Random random = new Random();
            String[] opciones = {"A", "B", "C", "D", "-"};

            for (int i = 1; i <= 10; i++) {
                String respuesta = opciones[random.nextInt(opciones.length)];
                System.out.println(codigoExamen + ";" + hilo.getName() + "; Pregunta " + i + ";" + respuesta);
            }
        } else {
            System.out.println("Agotado tiempo de espera y"+" no hay más exámenes");
        }
    }
}
