public class ProductorColor implements Runnable {
    private BufferColor buffer;
    private static final int[] colores = {2, 4, 1, 3}; // Cambiado el orden de los números y colores
    private Thread hilo;

    public ProductorColor(BufferColor buffer) {
        this.buffer = buffer;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        int color = colores[(int) (Math.random() * colores.length)];
        buffer.poner(color);
        System.out.println("Producido: " + BufferColor.obtenerColor(color));
    }
}
