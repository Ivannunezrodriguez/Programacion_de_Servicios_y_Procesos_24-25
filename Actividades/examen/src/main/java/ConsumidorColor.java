class ConsumidorColor implements Runnable {
    private BufferColor buffer;
    private Thread hilo;

    public ConsumidorColor(BufferColor buffer) {
        this.buffer = buffer;
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        String color = buffer.sacar();
        System.out.println("Consumido: " + color);
    }
}
