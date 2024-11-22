import java.util.Random;

public class Examinado implements Runnable {
    private Thread hilo;
    private BufferExamenes buffer;

    public Examinado(String alumno, BufferExamenes generador) {
        this.buffer = generador;
        this.hilo = new Thread(this, alumno);
        this.hilo.start();
    }

    @Override
    public void run() {
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
            Random random = new Random();
            String[] respuestas = {"A", "B", "C", "D", "-"};
            for (int i = 1; i <= 10; i++) {
                String respuesta = respuestas[random.nextInt(respuestas.length)];
                System.out.println(codigoExamen + ";" + hilo.getName() + "; Pregunta " + i + ";" + respuesta);
                try {
                    Thread.sleep(100); // Simula el tiempo de respuesta
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } else {
            System.out.println("Agotado tiempo de espera y no hay más exámenes");
        }
    }
}
