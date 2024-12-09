class ProductorColor implements Runnable {
    private static final String[] colores = {"Rojo", "Verde", "Amarillo", "Azul"};
    private BufferColor buffer;
    private Thread hilo;

    public ProductorColor(BufferColor buffer) {
        this.buffer = buffer;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        String color = colores[(int) (Math.random() * colores.length)];
        buffer.poner(color);
        System.out.println("Producido: " + color);
    }
}

