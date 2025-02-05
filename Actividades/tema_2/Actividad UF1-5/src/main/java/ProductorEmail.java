public class ProductorEmail implements Runnable {
    private static int contadorId = 0;
    private final BufferEmail buffer;
    private final String nombre;

    public ProductorEmail(BufferEmail buffer, String nombre) {
        this.buffer = buffer;
        this.nombre = nombre;
    }

    private static synchronized int generarId() {
        return ++contadorId;
    }

    @Override
    public void run() {
        // Se producen 10 emails por cada productor
        for (int i = 0; i < 10; i++) {
            int idEmail = generarId();
            Email email = new Email(
                    idEmail,
                    "destinatario" + idEmail + "@gmail.com",
                    nombre + "@gmail.com",
                    "Asunto " + idEmail,
                    "Cuerpo del mensaje " + idEmail
            );

            // Se incluye email en el buffer
            buffer.poner(email);
            System.out.println(nombre + " ha añadido email ID: " + idEmail);

            try {
                Thread.sleep(500); // Cada 0.5 segundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
