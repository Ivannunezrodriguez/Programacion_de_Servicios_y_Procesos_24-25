public class ProductorEmail implements Runnable {
    private static int contadorId = 0;
    private BufferEmail buffer;
    private String nombre;

    public ProductorEmail(BufferEmail buffer, String nombre) {
        this.buffer = buffer;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // se producen 10 emails
        for (int i = 0; i < 10; i++) {
            Email email = new Email(++contadorId, "destinatario" + contadorId + "@gmail.com",
                    nombre + "@gmail.com", "Asunto " + contadorId, "Cuerpo del mensaje " + contadorId);
// se incluye email
            buffer.poner(email);

            try {
                //cada 0.5 segundos
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
