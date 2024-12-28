public class ConsumidorEmail implements Runnable {
    private BufferEmail buffer;
    private String nombre;

    public ConsumidorEmail(BufferEmail buffer, String nombre) {
        this.buffer = buffer;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // siempre que haya email
        while (true) {
            Email email = buffer.sacar();
            //simula envio
            System.out.println(nombre + " ha procesado: " + email);


        }
    }
}
