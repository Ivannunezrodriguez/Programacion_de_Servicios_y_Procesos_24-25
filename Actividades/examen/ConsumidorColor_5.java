public class ConsumidorColor implements Runnable {
    private BufferColor buffer;
    private Thread hilo;

    public ConsumidorColor(BufferColor buffer) {
        this.buffer = buffer;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        Integer color = buffer.sacar();
        String colorNombre = (color != null) ? BufferColor.obtenerColor(color) : "Negro";
        System.out.println("Consumido: " + colorNombre);
    }
}
