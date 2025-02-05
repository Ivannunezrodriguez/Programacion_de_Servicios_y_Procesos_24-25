public class ConsumidorEmail implements Runnable {
    private final BufferEmail buffer;
    private final String nombre;
    private static int emailsConsumidos = 0;
    private static final int TOTAL_EMAILS = 30; // 3 productores * 10 emails cada uno

    public ConsumidorEmail(BufferEmail buffer, String nombre) {
        this.buffer = buffer;
        this.nombre = nombre;
    }

    private static synchronized void incrementarContador() {
        emailsConsumidos++;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (ConsumidorEmail.class) {
                if (emailsConsumidos >= TOTAL_EMAILS) {
                    break; // Si ya se procesaron todos los emails, salir del bucle
                }
            }

            Email email = buffer.sacar();
            if (email != null) {
                System.out.println(nombre + " ha leído: " + email);
                incrementarContador();
            }

            try {
                Thread.sleep(200); // Simulación de envío
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
